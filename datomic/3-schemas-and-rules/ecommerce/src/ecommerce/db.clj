(ns ecommerce.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [ecommerce.model :as model]
            [schema.core :as s]
            [clojure.walk :as walk]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn abre-conexao! []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco! []
  (d/delete-database db-uri))

; Produtos 
; id?
; nome String 1 ==> Computador Novo
; slug String 1 ==> /computador_novo
; preco pronto flutuante 1 ==> 3500.10
; categoria_id integer ==> 3

; id_entidade atributo valor
; 15 :produto/nome Computador Novo
; 15 :produto/slug /computador_novo
; 15 :produto/preco 3500.10
; 17 :produto/nome Telefone Caro
; 17 :produto/slug /telefone
; 17 :produto/preco 8888.88

(def schema [; Produtos
             {:db/ident       :produto/nome
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "O nome de um produto"}
             {:db/ident       :produto/slug
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "O caminho para acessar esse produto via http"}
             {:db/ident       :produto/preco
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "O preco de um produto com precisao monetaria"}
             {:db/ident       :produto/palavra-chave
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/many}
             {:db/ident       :produto/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             {:db/ident       :produto/categoria
              :db/valueType   :db.type/ref
              :db/cardinality :db.cardinality/one}

             ; Categorias
             {:db/ident       :categoria/nome
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :categoria/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}

             ; Transacoes
             {:db/ident       :tx-data/ip
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}])

(s/defn adiciona-produtos-ou-altera-produtos!
  ([conn produtos :- [model/Produto]]
   (d/transact conn produtos))
  ([conn produtos :- [model/Produto] ip]
   (let [db-add-ip [:db/add "datomic.tx" :tx-data/ip ip]]
     (d/transact conn (conj produtos db-add-ip)))))



(defn cria-schema! [conn]
  (d/transact conn schema))

(defn dissoc-db-id [entidade]
  (if (map? entidade)
    (dissoc entidade :db/id)
    entidade))

(defn datomic-para-entidade [entidades]
  (walk/prewalk dissoc-db-id entidades))

; o maybe permite nil
; nil permite nullpointerexception
; nullpointerexception permite um inferno de exceptions
; usamos maybe somente em retorno de funcao
; e somente quando fizer muito sentido
; iso eh, maybe nao eh usado em mapas, em mapas usamos as chaves opcionais
(s/defn um-produto :- (s/maybe model/Produto) [db produto-id :- java.util.UUID]
  (let [resultado (d/pull db '[* {:produto/categoria [*]}] [:produto/id produto-id])
        produto (datomic-para-entidade resultado)]
    (if (:produto/id produto)
      produto
      nil)))

(s/defn um-produto! :- model/Produto [db produto-id :- java.util.UUID]
  (let [produto (um-produto db produto-id)]
    (when (nil? produto) 
      (throw (ex-info "Nao encontrei uma entidade" {:type :errors/not-found :id produto-id})))
    produto))

(defn db-adds-de-atribuicao-de-categorias [produtos categoria]
  (reduce (fn [db-adds produto] (conj db-adds [:db/add
                                               [:produto/id (:produto/id produto)]
                                               :produto/categoria
                                               [:categoria/id (:categoria/id categoria)]]))
          []
          produtos))

(defn atribui-categorias! [conn produtos categoria]
  (let [a-transacionar (db-adds-de-atribuicao-de-categorias produtos categoria)]
    (d/transact conn a-transacionar)))

; como esses dois estao genericos poderiam ser um so
; mas vamos manter dois poise se utilizarmos schema fica mais facil de trabalhar
(s/defn adiciona-categorias! [conn categorias :- [model/Categoria]]
  (d/transact conn categorias))

; pull generico, vantagem preguica, desvantagem pode trazer mais do que eu queira
(s/defn todos-os-produtos :- [model/Produto] [db]
  (datomic-para-entidade (d/q '[:find [(pull ?entidade [* {:produto/categoria [*]}]) ...]
         :where
         [?entidade :produto/nome]] db)))

(s/defn todas-as-categorias :- [model/Categoria] [db]
  (datomic-para-entidade (d/q '[:find [(pull ?categoria [*]) ...]
         :where [?categoria :categoria/id]] db)))

(defn cria-dados-de-exemplo [conn]
  (def eletronicos (model/nova-categoria "Eletronicos"))
  (def esporte (model/nova-categoria "Esporte"))
  (pprint @(adiciona-categorias! conn [eletronicos esporte]))

  (def computador (model/novo-produto (model/uuid) "Computador Novo" "/computador-novo" 2500.10M))
  (def celular-caro (model/novo-produto (model/uuid) "Celular caro" "/celular" 8888.83M))
  ;(def calculadora {:produto/nome "Calculadora com 4 operacoes"})
  (def celular-barato (model/novo-produto "Celular barato" "/celular-barato" 0.1M))
  (def xadrez (model/novo-produto "Tabuleiro de Xadrez" "/tabuleiro-de-xadrez" 30M))
  (pprint @(adiciona-produtos-ou-altera-produtos! conn [computador celular-caro celular-barato xadrez] "255.255.255.0"))

  (atribui-categorias! conn [computador celular-caro celular-barato] eletronicos)
  (atribui-categorias! conn [xadrez] esporte))

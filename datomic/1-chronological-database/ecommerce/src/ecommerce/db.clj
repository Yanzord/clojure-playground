(ns ecommerce.db
  (:require [datomic.api :as d]
            [ecommerce.db :as db]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn abre-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (d/delete-database db-uri))


; Produtos 
; id?
; nome String 1 ==> Computador Novo
; slug String 1 ==> /computador_novo
; preco pronto flutuante 1 ==> 3500.10

; id_entidade atributo valor
; 15 :produto/nome Computador Novo
; 15 :produto/slug /computador_novo
; 15 :produto/preco 3500.10
; 17 :produto/nome Telefone Caro
; 17 :produto/slug /telefone
; 17 :produto/preco 8888.88

(def schema [{:db/ident       :produto/nome
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
              :db/doc         "O preco de um produto com precisao monetaria"}])

(defn cria-schema [conn]
  (d/transact conn schema))

; pull explicito atributo a atributo
;; (defn todos-os-produtos [db]
;;   (d/q '[:find (pull ?entidade [:produto/nome :produto/preco :produto/slug])
;;          :where
;;          [?entidade :produto/nome]] db))

; pull generico, vantagem preguica, desvantagem pode trazer mais do que eu queira
(defn todos-os-produtos [db]
  (d/q '[:find (pull ?entidade [*])
         :where
         [?entidade :produto/nome]] db))

; no sql eh comum fazer:
; String sql = "meu codigo sql"
; conexao.query(sql)

; esse aqui eh similar ao String sql
; eh comum e voce pode querer extrarir em um def ou let
; esse -q eh notacao hungara, indica o TIPO, nao parece ser legal em clojure
; vc vai encontrar esse padrao em alguns exemplos e doc
; nao recomendamos notacao hungara dessa maneira, ainda menos abreviada
(def todos-os-produtos-por-slug-fixo-q
  '[:find ?entidade
    :where [?entidade :produto/slug "/computador-novo"]])

(defn todos-os-produtos-por-slug-fixo [db]
  (d/q todos-os-produtos-por-slug-fixo-q db))

; nao estou usando notacao hungara e extract
; eh comum no sql: String sql = "select * from where slug = ::SLUG::"
; conexao.query(sql, {::SLUG:: "/computador-novo"})
(defn todos-os-produtos-por-slug [db slug]
  (d/q '[:find ?entidade
         :in $ ?slug-a-ser-buscado ; proposital diferente da variavel para evitar erros
         :where [?entidade :produto/slug ?slug-a-ser-buscado]] db slug))

; entity => ?entidade => ?produto => ?p
; se nao vai usar... _
(defn todos-os-slugs [db]
  (d/q '[:find ?slug
         :where [_ :produto/slug ?slug]] db))

(defn todos-os-produtos-por-preco [db]
  (d/q '[:find ?nome ?preco
         :keys nome preco
         :where [?produto :produto/preco ?preco]
                [?produto :produto/nome ?nome]] db))

; estou sendo explicitonos campos 1 a 1
(defn todos-os-produtos-por-preco [db]
  (d/q '[:find ?nome ?preco
         :keys produto/nome produto/preco
         :where [?produto :produto/preco ?preco]
         [?produto :produto/nome ?nome]] db))

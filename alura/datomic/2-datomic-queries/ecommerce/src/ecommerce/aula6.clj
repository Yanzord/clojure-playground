(ns ecommerce.aula6
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model]))

(def conn (db/abre-conexao!))

(db/cria-schema! conn)

(def eletronicos (model/nova-categoria "Eletronicos"))
(def esporte (model/nova-categoria "Esporte"))
(pprint @(db/adiciona-categorias! conn [eletronicos esporte]))

(def categorias (db/todas-as-categorias (d/db conn)))
(pprint categorias)

(def computador (model/novo-produto (model/uuid) "Computador Novo" "/computador-novo" 2500.10M))
(def celular-caro (model/novo-produto (model/uuid) "Celular caro" "/celular" 8888.83M))
(def calculadora {:produto/nome "Calculadora com 4 operacoes"})
(def celular-barato (model/novo-produto "Celular barato" "/celular-barato" 0.1M))
(def xadrez (model/novo-produto "Tabuleiro de Xadrez" "/tabuleiro-de-xadrez" 30M))
(pprint @(db/adiciona-produtos! conn [computador celular-caro calculadora celular-barato xadrez] "255.255.255.0"))

(db/atribui-categorias! conn [computador celular-caro celular-barato] eletronicos)
(db/atribui-categorias! conn [xadrez] esporte)

; podemos fazer db/add com nested maps (aninhados)
(pprint @(db/adiciona-produtos! conn [{:produto/nome "Camiseta"
                                       :produto/slug "/camiseta"
                                       :produto/preco 30M
                                       :produto/id (model/uuid)
                                       :produto/categoria {:categoria/nome "Roupas"
                                                           :categoria/id (model/uuid)}}] "20.216.222.12"))

; se a categoria ja existe, fazendo lookup ref
(pprint @(db/adiciona-produtos! conn [{:produto/nome "Dama"
                                       :produto/slug "/dama"
                                       :produto/preco 15M
                                       :produto/id (model/uuid)
                                       :produto/categoria [:categoria/id (:categoria/id esporte)]}]))

(pprint (db/todos-os-produtos (d/db conn)))

(pprint (db/todos-os-produtos-mais-caros (d/db conn)))
(pprint (db/todos-os-produtos-mais-baratos (d/db conn)))
(pprint (db/todos-os-produtos-do-ip (d/db conn) "255.255.255.0"))
(pprint (db/todos-os-produtos-do-ip (d/db conn) "20.216.222.12"))
(pprint (db/todos-os-produtos-do-ip (d/db conn) "205.216.222.12"))


;(db/apaga-banco!)

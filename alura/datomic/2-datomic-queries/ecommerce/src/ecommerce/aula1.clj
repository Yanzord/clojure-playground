(ns ecommerce.aula1
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model]))

(def conn (db/abre-conexao!))

(db/cria-schema! conn)

(let
 [computador (model/novo-produto (model/uuid) "Computador Novo" "/computador-novo" 2500.10M)
  celular-caro (model/novo-produto (model/uuid) "Celular caro" "/celular" 8888.83M)
  calculadora {:produto/nome "Calculadora com 4 operacoes"}
  celular-barato (model/novo-produto "Celular barato" "/celular-barato" 0.1M)]
  (pprint @(d/transact conn [computador celular-caro calculadora celular-barato])))

(def produtos (db/todos-os-produtos (d/db conn)))
(pprint produtos)
(def primeiro-dbid 
  (-> produtos 
      first
      first
      :db/id))

(pprint (db/um-produto-por-dbid (d/db conn) primeiro-dbid))

(def primeiro-produto-id
  (-> produtos
      first
      first
      :produto/id))

(pprint (db/um-produto (d/db conn) primeiro-produto-id))

;(db/apaga-banco)

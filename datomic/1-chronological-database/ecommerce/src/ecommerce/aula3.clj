(ns ecommerce.aula3
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(let
 [computador (model/novo-produto "Computador Novo" "/computador_novo" 2500.10M)
  celular-caro (model/novo-produto "Celular caro" "/celular" 8888.83M)
  calculadora {:produto/nome "Calculadora com 4 operacoes"}
  celular-barato (model/novo-produto "Celular barato" "/celular-barato" 0.1M)]
  (d/transact conn [computador celular-caro calculadora celular-barato]))

(pprint (db/todos-os-produtos (d/db conn)))

(db/apaga-banco)

(ns ecommerce.aula2
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(def computador (model/novo-produto (model/uuid) "Computador Novo" "/computador-novo" 2500.10M))
(def celular-caro (model/novo-produto (model/uuid) "Celular caro" "/celular" 8888.83M))
(def calculadora {:produto/nome "Calculadora com 4 operacoes"})
(def celular-barato (model/novo-produto "Celular barato" "/celular-barato" 0.1M))

(pprint @(d/transact conn [computador celular-caro calculadora celular-barato]))

(def produtos (db/todos-os-produtos (d/db conn)))
(pprint produtos)

; no momento que uso um identificador igual a algo que ja existe nao eh uma nova entidade
; eh uma atualizacao da entidade existente
(def celular-barato-2 (model/novo-produto (:produto/id celular-barato) "CELULAR BARATO!!!!!" "/celular-baratissimo" 0.001M))
(pprint celular-barato-2)
(pprint @(d/transact conn [celular-barato-2]))

(def produtos (db/todos-os-produtos (d/db conn)))
(pprint produtos)

(db/apaga-banco)

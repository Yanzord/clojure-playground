(ns ecommerce.aula1
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(let
 [computador (model/novo-produto "Computador Novo" "/computador_novo" 2500.10M)]
  (d/transact conn [computador]))

; o banco no instante que executo a linha
(def db (d/db conn))

(d/q '[:find ?entidade
       :where
       [?entidade :produto/nome]] db)
(let
 [celular-caro (model/novo-produto "Celular caro" "/celular" 8888.83M)]
  (d/transact conn [celular-caro]))

; tirando uma nova fotografia (SNAPSHOT) do banco
(def db (d/db conn))

(d/q '[:find ?entidade
       :where
       [?entidade :produto/nome]] db)

(db/apaga-banco)

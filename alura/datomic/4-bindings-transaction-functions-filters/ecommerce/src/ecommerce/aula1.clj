(ns ecommerce.aula1
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model])
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(db/apaga-banco!)
(def conn (db/abre-conexao!))
(db/cria-schema! conn)
(db/cria-dados-de-exemplo conn)

(pprint (db/todos-os-produtos (d/db conn)))

(pprint (db/todos-os-produtos-nas-categorias (d/db conn) ["Eletronicos"]))
(pprint (db/todos-os-produtos-nas-categorias (d/db conn) ["Eletronicos" "Esporte"]))
(pprint (db/todos-os-produtos-nas-categorias (d/db conn) ["Esporte"]))
(pprint (db/todos-os-produtos-nas-categorias (d/db conn) []))

(pprint (db/todos-os-produtos-nas-categorias-e-digital (d/db conn) ["Esporte"] false))
(pprint (db/todos-os-produtos-nas-categorias-e-digital (d/db conn) ["Eletronicos"] true))

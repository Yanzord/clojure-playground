(ns ecommerce.aula3
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

(def produtos (db/todos-os-produtos (d/db conn)))
(def primeiro (first produtos))
(pprint primeiro)

(pprint @(db/adiciona-variacao! conn (:produto/id primeiro) "Mouse gamer" 2700M))
(pprint @(db/adiciona-variacao! conn (:produto/id primeiro) "Mouse e fone gamer" 3000M))

(pprint (d/q '[:find (pull ?produto [*])
               :where [?produto :produto/nome]]
             (d/db conn)))

(pprint (db/todos-os-produtos (d/db conn)))

(pprint (db/total-de-produtos (d/db conn)))

(pprint @(db/remove-produto! conn (:produto/id primeiro)))

(pprint (d/q '[:find ?nome
               :where [_ :variacao/nome ?nome]]
             (d/db conn)))



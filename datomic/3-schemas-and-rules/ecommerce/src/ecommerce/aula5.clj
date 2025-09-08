(ns ecommerce.aula5
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

(pprint (db/todos-os-produtos-vendaveis (d/db conn)))

(def produtos (db/todos-os-produtos (d/db conn)))

(defn verifica-se-pode-vender [produto]
  (pprint "Analisando um produto")
  (pprint (:produto/estoque produto))
  (pprint (:produto/digital produto))
  (pprint (db/um-produto-vendavel (d/db conn) (:produto/id produto))))

(mapv verifica-se-pode-vender produtos)

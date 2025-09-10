(ns ecommerce.aula2
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

; o datomic suporte somente um dos identificadores, claro, nao foi imposta nenhuma restricao
(let
 [calculadora {:produto/nome "Calculadora com 4 operacoes"}]
  (d/transact conn [calculadora]))

; nao funciona pois se voce quer algo "vazio", eh so nao colocar
;(let
; [radio-relogio {:produto/nome "Radio com relogio" :produto/slug nil}]
;  (d/transact conn [radio-relogio]))

(let
 [celular-barato (model/novo-produto "Celular barato" "/celular-barato" 8888.83M)
  resultado @(d/transact conn [celular-barato])
  id-entidade (-> resultado :tempids vals first)]
  (pprint resultado)
  (pprint @(d/transact conn [[:db/add id-entidade :produto/preco 0.1M]]))
  (pprint @(d/transact conn [[:db/retract id-entidade :produto/slug "/celular-barato"]])))

(db/apaga-banco)

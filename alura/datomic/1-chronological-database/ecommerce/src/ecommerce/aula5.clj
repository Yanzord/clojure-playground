(ns ecommerce.aula5
  (:use clojure.pprint)
  (:require [datomic.api :as d])
  (:require [ecommerce.db :as db])
  (:require [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

; nao importa como voce extrai o MOMENTO da transacao
; o que importa eh vc usar esse momento para o seu as-of
; aqui no resultado da transacao eu podia usar o db-after
(let
 [computador (model/novo-produto "Computador Novo" "/computador-novo" 2500.10M)
  celular-caro (model/novo-produto "Celular caro" "/celular" 8888.83M)
  resultado @(d/transact conn [computador celular-caro])]
  (pprint resultado))

; meu snapshot, posso usar o momento real
(def fotografia-no-passado (d/db conn))

(let
 [calculadora {:produto/nome "Calculadora com 4 operacoes"}
  celular-barato (model/novo-produto "Celular barato" "/celular-barato" 0.1M)
  resultado @(d/transact conn [calculadora celular-barato])]
  (pprint resultado))

; um snapshot no instante do d/db = 4
(pprint (count (db/todos-os-produtos (d/db conn))))

; rodando a query num banco filtrado com dados do passado = 2
(pprint (count (db/todos-os-produtos fotografia-no-passado)))

; antes
(pprint (count (db/todos-os-produtos (d/as-of (d/db conn) #inst "2025-09-02T17:35:35.410"))))
; no meio
(pprint (count (db/todos-os-produtos (d/as-of (d/db conn) #inst "2025-09-02T17:34:35.410"))))
; depois
(pprint (count (db/todos-os-produtos (d/as-of (d/db conn) #inst "2025-09-02T17:36:35.410"))))

(db/apaga-banco)

(ns ecommerce.aula3
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

(pprint @(db/adiciona-produtos! conn [computador celular-caro calculadora celular-barato xadrez]))

(def produtos (db/todos-os-produtos (d/db conn)))
(pprint produtos)

(db/atribui-categorias! conn [computador celular-caro celular-barato] eletronicos)

(db/atribui-categorias! conn [xadrez] esporte)

(def produtos (db/todos-os-produtos (d/db conn)))
(pprint produtos)

;(db/apaga-banco!)

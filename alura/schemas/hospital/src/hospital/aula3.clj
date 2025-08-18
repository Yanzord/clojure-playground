(ns hospital.aula3
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int? 'inteiro-positivo))

(def Paciente
  {:id PosInt :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- PosInt nome :- s/Str]
  {:id id :nome nome})

(pprint (novo-paciente 15 "Yan"))
;(pprint (novo-paciente -15 "Yan"))

(defn maior-ou-igual-a-zero? [x] (>= x 0))
(def ValorFinanceiro (s/constrained s/Num maior-ou-igual-a-zero? 'maior-que-zero))

(def Pedido
  {:paciente Paciente
   :valor ValorFinanceiro
   :procedimento s/Keyword})

; sera que faz sentido "mini-schema" como aliases?
; (def procedimento s/Keyword)

(s/defn novo-pedido :- Pedido
  [paciente :- Paciente valor :- ValorFinanceiro procedimento :- s/Keyword]
  {:paciente paciente :valor valor :procedimento procedimento})

(pprint (novo-pedido (novo-paciente 15 "Yan") 15.53 :raio-x))
;(pprint (novo-pedido (novo-paciente 15 "Yan") -15.53 :raio-x))

(def Numeros [s/Num])
(pprint (s/validate Numeros [15]))
(pprint (s/validate Numeros [15 20 30 40 50]))
(pprint (s/validate Numeros [0]))
; nil nao eh numero, nao faz sentido
;(pprint (s/validate Numeros [nil]))
(pprint (s/validate Numeros []))
(pprint (s/validate Numeros nil))

; nil nao eh s/Num
;(pprint (s/validate s/Num nil))
; nil eh [s/Num]
(pprint (s/validate [s/Num] nil))

(def Plano [s/Keyword])
(pprint (s/validate Plano [:raio-x]))

(def Paciente
  {:id PosInt :nome s/Str :plano Plano})

(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano [:raio-x :ultrassom]}))
(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano [:raio-x]}))
(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano []}))
(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano nil}))
; plano eh uma keyword obrigatoria no mapa, mas ela pode ter um valor vazio (nil, [])
;(pprint (s/validate Paciente {:id 15 :nome "Yan"}))

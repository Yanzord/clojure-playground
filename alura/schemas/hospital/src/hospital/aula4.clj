(ns hospital.aula4
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int? 'inteiro-positivo))

(def Plano [s/Keyword])
(def Paciente
  {:id PosInt
   :nome s/Str
   :plano Plano
   (s/optional-key :nascimento) s/Str})

(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano [:raio-x :ultrassom]}))
(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano [:raio-x]}))
(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano []}))
(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano nil}))
(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano [] :nascimento "02/04/1998"}))

; esse eh um outro tipo de uso de mapas no mundo real
; Pacientes
; { 15 {PACIENTE} 32 {PACIENTE} }

(def Pacientes
  {PosInt Paciente})

(pprint (s/validate Pacientes {}))

(let [yan {:id 15 :nome "Yan" :plano [:raio-x]}
      daniela {:id 20 :nome "Daniela" :plano []}]
  (pprint (s/validate Pacientes {15 yan 20 daniela}))
  ;(pprint (s/validate Pacientes {-15 yan}))
  ;(pprint (s/validate Pacientes {15 15}))
  ;(pprint (s/validate Pacientes {15 {:id 15 :nome "Yan"}}))
  )

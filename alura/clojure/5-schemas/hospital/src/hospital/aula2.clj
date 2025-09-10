(ns hospital.aula2
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

;(s/defrecord Paciente 
;             [id :- Long nome :- s/Str])

;(pprint (Paciente. 15 "Yan"))

;(pprint (map->Paciente {"15" "Yan"}))

(def Paciente 
  "Schema de um paciente"
  {:id s/Num :nome s/Str})


(pprint (s/explain Paciente))
(pprint (s/validate Paciente {:id 15 :nome "Yan"}))

; typo eh pego pelo schema, mas poderiamos argumentar que esse
; tipo de erro seria pego em testes automatizados com cobertura boa
;(pprint (s/validate Paciente {:id 15 :name "Yan"}))
; mas... entra a questao de QUERER ser forward compatible OU NAO
; entender esse trade-off
; sistemas externos nao me quebrarao ao adicionar campos novos (forward compatible)
; no nosso validate nao estamos sendo forward compatible (pode ser interessante quando quero analisar mudancas)
;(pprint (s/validate Paciente {:id 15 :nome "Yan" :plano [:raio-x]}))

; chaves que sao keywords em schemas sao por padrao OBRIGATORIAS
;(pprint (s/validate Paciente {:id 15}))

; tipo de retorno com schema forca a validacao na saida da funcao
;(s/defn novo-paciente :- Paciente
;  [id :- s/Num nome :- s/Str]
;  {:id id :nome nome :plano [:raio-x]})

(s/defn novo-paciente :- Paciente
  [id :- s/Num nome :- s/Str]
  {:id id :nome nome})

(pprint (novo-paciente 15 "Yan"))

; funcao pura, simples, facil de testar
(defn estritamente-positivo?
  [x]
  (> x 0))

;(def EstritamentePositivo (s/pred estritamente-positivo?))
(def EstritamentePositivo (s/pred estritamente-positivo? 'estritamente-positivo))

(s/validate EstritamentePositivo 15)
;(s/validate EstritamentePositivo 0)
;(s/validate EstritamentePositivo -15)

(def Paciente
  "Schema de um paciente"
  {:id (s/constrained s/Int pos?) :nome s/Str})
; eh por isso que eh importante debulhar documentacao
; ja existe pos? e ja existe pos-int?
; dica eh sempre debulhar documentacao

(pprint (s/validate Paciente {:id 15 :nome "Yan"}))
;(pprint (s/validate Paciente {:id -15 :nome "Yan"}))
;(pprint (s/validate Paciente {:id 15 :nome "Yan"}))


; um caminho que eu nao seguiria: lambas dentro dos schemas
; os nomes ficam confusos ou a legibilidade do schema se perde
; alem de que voce perdeu a facilidade de testar aquele lambda isoladamente
;(def Paciente
;  "Schema de um paciente"
;  {:id (s/constrained s/Int #(> % 0) 'inteiro-estritamente-positivo) :nome s/Str})
;(pprint (s/validate Paciente {:id 15 :nome "Yan"}))
;(pprint (s/validate Paciente {:id -15 :nome "Yan"}))
;(pprint (s/validate Paciente {:id 15 :nome "Yan"}))

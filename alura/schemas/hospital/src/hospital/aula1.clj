(ns hospital.aula1
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente nao possui id" {:paciente paciente}))))

; { 15 [], 20 [], 25 [] }
(defn adiciona-visita
  [visitas paciente novas-visitas]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)
    (assoc visitas paciente novas-visitas)))

(defn imprime-relatorio-de-paciente [visitas paciente]
  (println "Visitas do paciente" paciente "sao" (get visitas paciente)))

(defn testa-uso-de-pacientes []
  (let [guilherme {:id 15 :nome "Yan"}
        daniela {:id 20 :nome "Daniela"}
        paulo {:id 25 :nome "Paulo"}
        ; uma variacao com reduce, mais natural
        pacientes (reduce adiciona-paciente {} [guilherme daniela paulo])

        ; uma variacao com shadowing, fica feio
        visitas {}
        visitas (adiciona-visita visitas 15 ["01/01/2019"])
        visitas (adiciona-visita visitas 20 ["01/01/2019" "01/01/2020"])
        visitas (adiciona-visita visitas 15 ["01/03/2019"])]
    (pprint pacientes)
    (pprint visitas)

    ; cacas grandes na vida pois estou usando o simbolo diferente
    ; em varios lugares do meu programa com significados diferentes
    (imprime-relatorio-de-paciente visitas daniela)
    (imprime-relatorio-de-paciente visitas 20)))

(testa-uso-de-pacientes)


(pprint (s/validate Long 15))
;(pprint (s/validate Long "Guilherme"))


(s/set-fn-validation! true)

(s/defn teste-simples 
  [x :- Long]
  (println x))

(teste-simples 30)
;(teste-simples "Guilherme")


(s/defn imprime-relatorio-de-paciente 
  [visitas paciente :- Long]
  (println "Visitas do paciente" paciente "sao" (get visitas paciente)))

; agora conseguimos o erro em tempo de *execucao* que diz
; que o valor passado como parametro nao condiz com o schema Long
;(testa-uso-de-pacientes)


(s/defn novo-paciente
  [id :- Long nome :- String]
  {:id id :nome nome})
 
(pprint (novo-paciente 15 "Yan"))
; gera erro
;(pprint (novo-paciente "Yan" 15))




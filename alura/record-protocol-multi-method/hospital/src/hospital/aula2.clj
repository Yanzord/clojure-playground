(ns hospital.aula2
  (:use clojure.pprint))

;(defrecord Paciente [id nome nascimento])

; Paciente Plano de saude -> + Plano de saude
; Paciente particular -> 0

; caminho horripilante com provaveis problemas horriveis e tipos 2ˆn
;(defrecord PacientePlanoDeSaude HERDA Paciente [plano])

; digitacao nao eh o maaior problema da nossa vida
(defrecord PacienteParticular [id nome nascimento])
(defrecord PacientePlanoDeSaude [id nome nascimento plano])

; REGRAS DIFERENTES PARA TIPOS DIFERENTES
; deve-assinar-pre-autorizacao?
; Particular -> valor >= 50
; PlanoDeSaude -> procedimento NAO esta no plano

; VANTAGEM: tudo no mesmo lugar
; DESVANTAGEM: tudo no mesmo lugar
;(defn deve-assinar-pre-autorizacao?
; [paciente procedimento valor]
;  (if (= PacienteParticular (type paciente))
;    (>= valor 50)
;    ; assumindo que existe os dois tipos
;    (if (= PacientePlanoDeSaude (type paciente))
;      (let [plano (get paciente :plano)]
;        (not (some #(= % procedimento) plano)))
;      true)))

(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (>= valor 50)))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (let [plano (:plano paciente)]
      (not (some #(= % procedimento) plano)))))
; contains? -> verifica o indice... e indice vc fica dependendo da estrutura de dados, vetor eh numerico


; alternativa seria implementar diretamente
;(defrecord PacienteParticular 
;           [id nome nascimento]
;  Cobravel
;  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
;    (>= valor 50)))


(let [particular (->PacienteParticular 15 "Yan" "02/04/1998")
      plano (->PacientePlanoDeSaude 15 "Yan" "02/04/1998" [:raio-x :ultrassom])]
  (pprint (deve-assinar-pre-autorizacao? particular :raio-x 40))
  (pprint (deve-assinar-pre-autorizacao? plano :raio-x 4099990))
  (pprint (deve-assinar-pre-autorizacao? plano :coleta-de-sangue 4099990)))


; Uma variacao baseada na palestra a seguir, mas com extend-type e com gregoriancalendar
; From Sean Devlin's talk on protocols at Clojure Conj
(defprotocol Dateable
  (to-ms [this]))

(extend-type java.lang.Number
  Dateable
  (to-ms [this] this))

(pprint (to-ms 56))

(extend-type java.util.Date
  Dateable
  (to-ms [this] (.getTime this)))


(pprint (to-ms (java.util.Date.)))

(extend-type java.util.Calendar
  Dateable
  (to-ms [this] (to-ms (.getTime this))))

(pprint (to-ms (java.util.GregorianCalendar.)))

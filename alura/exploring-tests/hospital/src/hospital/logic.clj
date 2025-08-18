(ns hospital.logic)

; Test Driven Development
; Test Driven Design


; existe um problema de condicional quando departamento nao existe
;(defn cabe-na-fila? 
;  [hospital departamento]
;  (-> hospital
;      departamento
;      count
;      (< 5)))

; funciona para o caso de nao ter o departamento
;(defn cabe-na-fila?
;  [hospital departamento]
;  (when-let [fila (get hospital departamento)]
;    (-> fila
;        count
;        (< 5))))

; tambem funciona mas usa o some->
; desvantagem/vantagem "menos explicito"
; desvantagem qq um que der nil, devolve nil
(defn cabe-na-fila?
  [hospital departamento]
  (some-> hospital
          departamento
          count
          (< 5)))

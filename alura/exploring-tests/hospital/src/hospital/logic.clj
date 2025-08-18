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

;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;  (update hospital departamento conj pessoa)
;    (throw (ex-info "Nao cabe ninguem neste departamento" {:paciente pessoa}))))


;(defn chega-em\
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (IllegalStateException. "Nao cabe ninguem neste departamento"))))


; nil?
;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (IllegalStateException. "Nao cabe ninguem neste departamento"))))

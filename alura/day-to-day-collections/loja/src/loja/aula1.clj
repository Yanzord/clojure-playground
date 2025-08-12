(ns loja.aula1)

; ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas" ]
; {"guilherme" 37, "paulo" 39}
; '(1 2 3 4 5)
; [[0 1]]
; #{}

; map
; reduce
; filter

(map println ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas"])
(println (first ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas"]))
(println (rest ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas"]))
(println (rest []))
(println (next ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas"]))
(println (next []))
(println (seq []))
(println (seq [1 2 3 5]))

(println "\n\n\n\nMEU MAPA com parada")

(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (funcao primeiro)
    (meu-mapa funcao (rest sequencia))))

;(meu-mapa println ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas" ])

(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if primeiro
      (do
        (funcao primeiro)
        (meu-mapa funcao (rest sequencia))))))

(meu-mapa println ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas"])
(meu-mapa println ["daniela" false "lucia" "julio" "thomas" "darci" "maria" "lucas"])

(println "\n\n\n\nMEU MAPA com parada no nulo")

(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (meu-mapa funcao (rest sequencia))))))

(meu-mapa println ["daniela" "yan" "lucia" "julio" "thomas" "darci" "maria" "lucas"])
(meu-mapa println ["daniela" false "lucia" "julio" "thomas" "darci" "maria" "lucas"])
(meu-mapa println [])
(meu-mapa println nil)

; (meu-mapa println (range 100000))

; TAIL RECURSION
(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (recur funcao (rest sequencia))))))

(meu-mapa println (range 5000))

; NAO EH TAIL, NAO PODE!!!
; (defn meu-mapa
;  [funcao sequencia]
;  (let [primeiro (first sequencia)]
;    (if (not (nil? primeiro))
;      (do
;        (recur funcao (rest sequencia))
;        (funcao primeiro)))))

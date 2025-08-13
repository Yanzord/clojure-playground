(ns loja.aula5
  (:require [loja.db :as l.db]
            [loja.logic :as l.logic]))


(defn gastou-bastante?
  [info-do-usuario]
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)] 
  (println "keep" (keep gastou-bastante? resumo))
  (println "filter" (filter gastou-bastante? resumo)))


(println "tentando entender o keep")

(defn gastou-bastante?
  [info-do-usuario]
  (println "gostou-bastante?" info-do-usuario)
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)] 
  (println "keep" (keep gastou-bastante? resumo)))

(println "vamos isolar...")

(println (take 2(range 100000000000000000000000000)))
; a sequencia nao esta sendo "gulosa" (eager)

(let [sequencia (range 1000000000)]
  (println (take 2 sequencia))
  (println (take 2 sequencia)))
; esta sendo LAZY (preguicoso)!!!


(defn filtro1 [x]
  (println "filtro1" x)
  x)

(println (map filtro1 (range 10)))

(defn filtro2 [x]
  (println "filtro2" x)
  x)

(println (map filtro2 (map filtro1 (range 10))))

; CHUNKS de 32
(->> (range 50)
     (map filtro1)
     (map filtro2)
     println)

; lista ligada foi 100% lazy nesse cenario
(->> '(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50)
     (map filtro1)
     (map filtro2)
     println)

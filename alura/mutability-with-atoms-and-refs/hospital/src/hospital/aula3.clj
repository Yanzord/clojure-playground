(ns hospital.aula3
  (:use [clojure.pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

; simbolo que qualquer thread que acessar esse namespace vai ter acesso a ele com o valor padrao "yan"
(def nome "yan")

; redefinir o simbolo
(def nome 32567)

(let [nome "yan"]
  ; coisa 1
  ; coisa 2
  (println nome)
  ; nao estou refazendo o binding do simbolo local a este bloco
  ; criando um novo simbolo local a este bloco e ESCONDENDO o anterior
  ; SHADOWING
  (let [nome "brenda"]
    ; coisa 3
    ; coisa 4
    (println nome))
  (println nome))

(defn testa-atomao []
  (let [hospital-silveira (atom {:espera h.model/fila-vazia})]
    (println hospital-silveira)
    (pprint hospital-silveira)
    (println (deref hospital-silveira))
    (pprint @hospital-silveira)

    ; nao eh assim que eu altero o conteudo dentro de um atomo
    (pprint (assoc @hospital-silveira :laboratorio1 h.model/fila-vazia))
    (pprint @hospital-silveira)

    ; essa eh uma das maneiras de alterar conteudo de um atomo
    (swap! hospital-silveira assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-silveira)

    (swap! hospital-silveira assoc :laboratorio2 h.model/fila-vazia)
    (pprint @hospital-silveira)

    ; update tradicional imutavel, com dereferencia, que nao trara efeito
    (update @hospital-silveira :laboratorio1 conj "111")
    (pprint @hospital-silveira)

    ; indo pra swap
    (swap! hospital-silveira update :laboratorio1 conj "222")
    (pprint @hospital-silveira)
    ))


; (testa-atomao)




(defn chega-em-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado-logando :espera pessoa)
  (println "apos inserir" pessoa))0

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital)))))
  )
; forcando situacoes de retry
;(simula-um-dia-em-paralelo)


(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa)
  (println "apos inserir" pessoa)) 0

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

; sem forcar situacao de retry (busy retry), pode acontecer, mas pode nao acontecer
(simula-um-dia-em-paralelo)

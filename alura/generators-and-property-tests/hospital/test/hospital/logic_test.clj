(ns hospital.logic-test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]
            [hospital.model :as h.model]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [schema.core :as s]))

(s/set-fn-validation! true)

; sao testes ESCRITOS baseados em exemplos
(deftest cabe-na-fila?-test

  (testing "Que cabe numa fila vazia"
    (is (cabe-na-fila? {:espera []} :espera)))
  
  ; o doseq com um simbolo e uma sequencia gerada funciona
  ; mas... talvez nao seja o que queremos em example-based manual
  ; vamos ver um problema de 2 simbolos
  (testing "Que cabe pessoas em filas de tamanho ate 4 inclusive"
    (doseq [fila (gen/sample (gen/vector gen/string-alphanumeric 0 4) 100)]
      (is (cabe-na-fila? {:espera fila} :espera)))) 

  (testing "Que nao cabe na fila quando a fila esta cheia"

    (is (not (cabe-na-fila? {:espera [1 5 37 54 21]} :espera))))

; one of da borda do limite pra cima
  (testing "Que nao cabe na fila quando tem mais do que uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5 6]} :espera))))

  ; dentro das bordas
  (testing "Que cabe na fila quando tem gente mas nao esta cheia"
    (is (cabe-na-fila? {:espera [1 2 3 4]} :espera))
    (is (cabe-na-fila? {:espera [1 2]} :espera)))

  (testing "Que nao cabe quando o departamento nao existe"
    (is (not (cabe-na-fila? {:espera [1 2 3 4]} :raio-x)))))

; aqui tivemos um problema
; o doseq na unha gera uma multiplicacao de casos
; incluindo muuuuuuitos casos repetidos
; que nao tem nada a ligado com o que queremos
;; (deftest chega-em-test
;;   (testing "Que eh colocada uma pessoa em filas menores que 5"
;;     (doseq [fila (gen/sample (gen/vector gen/string-alphanumeric 0 4) 10)
;;             pessoa (gen/sample gen/string-alphanumeric 5)]
;;       (println pessoa fila)
;;       (is (= 1 1)) ; so pra mostrar que sao 50 asserts (10 * 5)
;;       )) 
;;   )

; muito importante lembrar que se vc esta rodando um repl continuo e recarregando os testes
; vc corre o risco de uma funcao que foi definida antigamente continuar carregada no seu namespace
; nesse caso lembrar de restatar o repl

; o teste a seguir eh generativo e funciona
; mas... o resultado dele parece MUITO uma copia do nosso codigo implementado
;{:espera (conj fila pessoa)} == o codigo que eu escrevi na minha logica
; significa que se eu coloquei um bug la, provavelmente coloquei um bug aqui, e ai vai dar true e o bug continua
(defspec coloca-uma-pessoa-em-filas-menores-que-5 100
  (prop/for-all 
   [fila (gen/vector gen/string-alphanumeric 0 4)
    pessoa gen/string-alphanumeric]
   (is (= {:espera (conj fila pessoa)} 
      (chega-em {:espera fila} :espera pessoa)))
   ))

; coloquei sufixo mas voce vai ver prefixo tambem no mundo
; nao sou o maior fa mas eh o que a vida nos oferece
(def nome-aleatorio-gen
  (gen/fmap clojure.string/join 
            (gen/vector gen/char-alphanumeric 5 10)))

(defn transforma-vetor-em-fila [vetor]
  (reduce conj h.model/fila-vazia vetor))

(def fila-nao-cheia-gen 
  (gen/fmap
   transforma-vetor-em-fila
   (gen/vector nome-aleatorio-gen 0 4)))

; abordagem razoavel porem horrivel, uma vez que usamos o tipo e o tipo do tipo
; para fazer um cond e pegar a exception que queremos
; uma alternativa seria usar bibliotecas como catch-dataa
; LOG AND RETHROW eh ruim. pq? pq se voce pegou, eh pq vc queria tratar
; a resposta? pq a linguagem nos forcou a jogar ex-info. nao eh que nos forcou
; mas todo mundo usa ex-info, entao nos forcou...
;; (defn transfere-ignorando-erro [hospital para]
;;   (try
;;     (transfere hospital :espera para)
;;     (catch clojure.lang.ExceptionInfo e
;;       (cond
;;         (= :fila-cheia (:type (ex-data e))) hospital
;;         :else (throw e)))))

; abordagem mais interessante pois evita log and rethrow
; mas perde o "poder" de ex-info (ExceptionInfo)
; e ainda tem o problema de que outras partes do meu codigo ou
; do codigo de outras pessoas pode jogar IllegalStateException
; e eu estou confundindo isso com fila cheia
; para resolver isso, so criando minha propria exception
; mas ai caio no boom de exceptions no sistema (tenho q criar varios tipos)
; OU, criar variacoes de tipos como fizemos no ex-info
; tem tambem todos os outros caminhos que discutimos no curso onde falamos sobre tratamento de erros
(defn transfere-ignorando-erro [hospital para]
  (try
    (transfere hospital :espera para)
    (catch IllegalStateException e
      hospital)))

(defspec transfere-tem-que-manter-a-quantidade-de-pessoas 50
  (prop/for-all 
   [espera (gen/fmap transforma-vetor-em-fila (gen/vector nome-aleatorio-gen 0 50))
    raio-x fila-nao-cheia-gen
    ultrassom fila-nao-cheia-gen
    vai-para (gen/vector (gen/elements [:raio-x :ultrassom]) 0 50)] 
   (let [hospital-inicial {:espera espera
                   :raio-x raio-x
                   :ultrassom ultrassom}
         hospital-final (reduce transfere-ignorando-erro hospital-inicial vai-para)] 
     (= (hospital.logic/total-de-pacientes hospital-inicial) 
        (hospital.logic/total-de-pacientes hospital-final))
     )
   ))








(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]))

(deftest cabe-na-fila?-test

  ; boundary tests
  ; exatamente na borda e one off. -1, +1, <=, >=, =,
  ; checklist na minha cabeca. poderia ser um checklist no papel

  ; borda do zero 
  (testing "Que cabe numa fila vazia"
    (is (cabe-na-fila? {:espera []} :espera)))
  
  ; borda do limite
  (testing "Que nao cabe na fila quando a fila esta cheia"
    
    ; eh de simples leitura pois eh sequencial
    ; mas a desvantagem eh que podemos errar em fazer coisas sequenciais
    ;(is (not (cabe-na-fila? {:espera [1 2 3 4 5]} :espera)))
    
    ; nao precisa ser sequencial e no mundo real nao eh
    ; portanto faca testes que nao sao sequenciais
    (is (not (cabe-na-fila? {:espera [1 5 37 54 21]} :espera)))
    )
  
  ; one of da borda do limite pra cima
  (testing "Que nao cabe na fila quando tem mais do que uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5 6]} :espera))))
  
  ; dentro das bordas
  (testing "Que cabe na fila quando tem gente mas nao esta cheia"
    (is (cabe-na-fila? {:espera [1 2 3 4]} :espera))
    (is (cabe-na-fila? {:espera [1 2]} :espera)))
  
  (testing "Que nao cabe quando o departamento nao existe"
    (is (not (cabe-na-fila? {:espera [1 2 3 4]} :raio-x)))))





(deftest chega-em-test 
  
  (testing "aceita pessoas enquanto cabem pessoas na fila"
; implementacao ruim pois testa que escrevemos o que escrevemos.
; isto eh, testa que erramos o que erramos. e que acertamos o que acertamos.
;    (is (= (update {:espera [1 2 3 4]} :espera conj 5) 
;           (chega-em {:espera [1 2 3 4]} :espera 5)))
    
    (is (= {:espera [1 2 3 4 5]}
           (chega-em {:espera [1 2 3 4]} :espera 5)))
    
    ; teste nao sequencial
    (is (= {:espera [1 2 5]}
           (chega-em {:espera [1 2]} :espera 5))))
  
  (testing "nao aceita quando nao cabe na fila"
    ; verificando que uma exception foi jogada
    ; codigo classico horrivel. usamos uma exception GENERICA.
    ; mas qq outro erro generico vai jogar essa exception, e nos vamos achar que deu certo quando deu errado
    
    ; strings de testo SOLTO sao super faceis de quebrar
    ;(is (thrown-with-msg? clojure.lang.ExceptionInfo "Nao cabe ninguem neste departamento"
    ;             (chega-em {:espera [1 35 42 64 21]} :espera 97)))
    
    ; mesmo que eu escolha uma exception do genero, perigoso!
    ;(is (thrown? IllegalStateException
    ;                 (chega-em {:espera [1 35 42 64 21]} :espera 97)))
    
    ; outra abordagem, no nil
    ; mas o perigo do swap, teriamos que trabalhar em outro ponto a condicao de erro
    ;(is (nil? (chega-em {:espera [1 35 42 64 21]} :espera 97)))
    
    ))

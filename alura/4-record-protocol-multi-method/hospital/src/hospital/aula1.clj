(ns hospital.aula1
 (:use clojure.pprint))


(defn adiciona-paciente 
  "Os pacientes sao um mapa da seguinte forma { 15 {paciente 15}, 23 {paciente 23} }
   O paciente { :id 15 .... }"
  [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente sem id" {:paciente paciente}))))

(defn testa-uso-de-pacientes []
  (let [pacientes {}
        yan { :id 15 :nome "Yan" :nascimento "02/04/1998" }
        daniela { :id 20 :nome "Daniela" :nascimento "18/9/1982" }
        paulo { :nome "Paulo" :nascimento "10/10/1980" }]
    
    (pprint (adiciona-paciente pacientes yan))
    (pprint (adiciona-paciente pacientes daniela))
    (pprint (adiciona-paciente pacientes paulo))))

;(testa-uso-de-pacientes)

(defrecord Paciente [id nome nascimento])

(pprint (->Paciente 15 "Yan" "02/04/1998"))
(pprint (Paciente. 15 "Yan" "02/04/1998"))
(pprint (map->Paciente {:id 15 :nome "Yan" :nascimento "02/04/1998"}))


(let [yan (->Paciente 15 "Yan" "02/04/1998")]
  (println (:id yan))
  (println (vals yan))
  (println (class yan))
  (println (record? yan))
  (println (.nome yan)))

(pprint (map->Paciente {:id 15 :nome "Yan" :nascimento "02/04/1998" :rg "111222333444"}))
(pprint (map->Paciente { :nome "Yan" :nascimento "02/04/1998" :rg "111222333444"}))

;(pprint (Paciente. "Yan" "02/04/1998"))
(pprint (Paciente. nil "Yan" "02/04/1998"))

(pprint (assoc (Paciente. nil "Yan" "02/04/1998") :id 15))
(pprint (class (assoc (Paciente. nil "Yan" "02/04/1998") :id 15)))

(pprint (= (->Paciente 15 "Yan" "02/04/1998") (->Paciente 15 "Yan" "02/04/1998")))
(pprint (= (->Paciente 15 "Yan" "02/04/1998") (->Paciente 20 "Yan" "02/04/1998")))

(ns hospital.core
  (:gen-class)
  (:use [clojure.pprint])
  (:require [hospital.model :as h.model]))

; espera

; laboratorio1
; laboratorio2
; laboratorio3 



(let [hospital-do-yan (h.model/novo-hospital)]
  (pprint hospital-do-yan))


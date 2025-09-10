(ns clojure-owasp.owasp4
  (:require [clojure.xml :as xml]))

;; deve-se criar um parser customizado de leitura de xml com medidas de seguranca para evitar
;; ataques de xml external entity (XXE)

(defn parse-document [input-stream]
  (xml/parse input-stream))

(defn get-document [uri]
  (-> uri
      slurp
      (.getBytes "UTF-8")
      java.io.ByteArrayInputStream.
      parse-document))

(println (get-document "resources/nasty.xml"))

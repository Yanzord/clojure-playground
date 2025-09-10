(ns clojure-owasp.owasp8)

(defn treat-dot-comms [word]
  (-> word
      (clojure.string/replace "." "")
      (clojure.string/replace "," ".")
      clojure.edn/read-string))

(println (treat-dot-comms "1111.1111,30"))
(println (treat-dot-comms "#=(+ 2 2)"))

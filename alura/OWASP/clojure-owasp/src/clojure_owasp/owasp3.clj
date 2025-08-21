(ns clojure-owasp.owasp3
  (:require [crypto.password.bcrypt :as password]))

(def database (atom {}))

(defn add [table doc]
  (swap! database update-in [table] conj doc))

(defn encrypted-password [password]
  (password/encrypt password))

(defn read-file [filename]
  (-> filename
      slurp
      clojure.string/split-lines))

(def common-passwords (read-file "src/common-passwords.txt"))

(defn is-common? [password]
  (some #(= password %) common-passwords))

(defn register-new-user [username password]
  (if (is-common? password)
    (throw (Exception. "Password is too common")) 
    (let [encrypted-password (encrypted-password password)]
      ;(println username password)
      (add "Users" {:username username :password encrypted-password}))))

;(println (register-new-user "yan.pereira" "pato123"))
;(println (register-new-user "guilherme.silveira" "senha"))


(defn continue [chain path parameters]
  (if chain
    (let [next-one (first chain)]
      (next-one (rest chain) path parameters))))

(defn execution-layer [chain path parameters]
  (println "executing for" path))

(defn do-upload [parameters]
  (println "uploading file"))

(defn upload-layer [chain path parameters]
  (if (:upload-file parameters)
    (do-upload parameters))
  (continue chain path parameters))

(defn log-layer [chain path parameters]
  ; esse log eh perigoso... por causa dos parametros
  (println path parameters)
  (continue chain path parameters)
  )

(defn service [path parameters]
  (let [chain [log-layer upload-layer execution-layer]]
    (continue chain path parameters)))




(service "/upload" {:uploaded-file "hi.txt"})
(service "/login" {:password "password"})



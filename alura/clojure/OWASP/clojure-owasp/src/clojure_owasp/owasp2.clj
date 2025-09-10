(ns clojure-owasp.owasp2
  (:require [crypto.password.bcrypt :as password]))

(def database (atom {}))

(defn add [table doc]
  (swap! database update-in [table] conj doc))

(defn register-new-user [username password]
  (add "Users" {:username username :password password}))

;(println (register-new-user "yan.pereira" "pato123"))

(defn encrypted-password [password]
  (password/encrypt password))

;(println (encrypted-password "pato123"))

;(password/check "pato123" (encrypted-password "pato123"))


(defn register-new-user [username password]
  (let [encrypted-password (encrypted-password password)]
    (add "Users" {:username username :password encrypted-password})))

;(println (register-new-user "yan.pereira" "pato123"))


(defn read-file [filename]
  (-> filename
      slurp
      clojure.string/split-lines))

(def common-passwords (read-file "resources/common-passwords.txt"))

(defn is-common? [password]
  (some #(= password %) common-passwords))

(println (is-common? "senha") (is-common? "aijsneiuahwe9iud"))


(defn register-new-user [username password]
  (if (is-common? password)
    (throw (Exception. "Password is too common"))
    (let [encrypted-password (encrypted-password password)]
      (add "Users" {:username username :password encrypted-password}))))

(println (register-new-user "yan.pereira" "pato123"))
(println (register-new-user "guilherme.silveira" "senha"))

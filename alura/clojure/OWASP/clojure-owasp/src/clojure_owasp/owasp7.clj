(ns clojure-owasp.owasp7
  (:require [crypto.password.bcrypt :as password]))

(def database (atom {}))

(defn add [username doc]
  (swap! database assoc-in [username] doc))

(defn encrypted-password [password]
  (password/encrypt password))

(defn register-new-user [username password]
  (let [encrypted-password (encrypted-password password)]
    (add "Users" {:username username :password encrypted-password})))

(defn read-file [filename]
  (-> filename
      slurp
      clojure.string/split-lines))

(def common-passwords (read-file "resources/common-passwords.txt"))

(defn is-common? [password]
  (some #(= password %) common-passwords))

(defn register-new-user [username password name]
  (if (is-common? password)
    (throw (Exception. "Password is too common"))
    (let [encrypted-password (encrypted-password password)]
      (add username {:username username :password encrypted-password :name name}))))

(println (register-new-user "yan.pereira" "pato123" "Yan Pereira"))
(println (register-new-user "guilherme.silveira" "gato123" "Guilherme Silveira"))

(defn load-user [username]
  (get @database username))

(def public-profile-template "<html>
                              <head><title>Welcome</title></head>
                              <body>
                              <h1>{{NAME}}</h1>
                              {{USERNAME}}
                              </body>
                              </html>")

(defn replace-symbol [content [symbol-name symbol-value]]
  (let [key (str "{{" symbol-name "}}")]
    (clojure.string/replace content key symbol-value)))

(defn render-template [content symbols]
  (reduce replace-symbol content symbols))

(defn view-public-profile [username]
  (let [user (load-user username)]
    (println username user)
    (render-template public-profile-template {"USERNAME" (:username user) 
                                              "NAME" (:name user)})))

(println @database)

(println (view-public-profile "yan.pereira"))


(println (register-new-user "guilherme.silveira" "gato123" "<script>alert('oi');</script>Guilherme Silveira"))

(println (view-public-profile "guilherme.silveira"))

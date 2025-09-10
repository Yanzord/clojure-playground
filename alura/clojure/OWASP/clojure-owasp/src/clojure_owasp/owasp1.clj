(ns clojure-owasp.owasp1)
(use '[clojure.java.shell :only [sh]])

;; versao com vulnerabilidade
;; (defn run-cluster [config-file]
;;   (let [command (str "/bin/kafka " config-file)] 
;;     (sh "bash" "-c" command)))

;; (run-cluster "server.properties; ls /")

(defn run-cluster [config-file]
  (sh "/bin/kafka" config-file))

;(run-cluster "server.properties")

; vulnerabilidade
(defn login [username password]
  (let [sql (str "select * from Users where username='" username "' and password='" password "'")]
    (println sql)
    ; executaria o sql
    ))

(login "yan" "123")
(login "yan" "' or id=1 having '1'='1'")
(login "yan" "' or admin=1 having '1'='1'")


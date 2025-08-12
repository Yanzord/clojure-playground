(ns functional-programming.aula6)

(def pedido {:mochila {:quantidade 2 :preco 80}
             :camiseta {:quantidade 3 :preco 40}})

(defn imprime-e-15
  [valor]
  (println "valor" (class valor) valor)
  15)

(println (map imprime-e-15 pedido))

(defn imprime-e-15
  [[chave valor]]
  (println chave "<e>" valor)
  valor)

(println (map imprime-e-15 pedido))

(defn preco-por-produto
  [[_ valor]]
  (* (:quantidade valor) (:preco valor)))

(println (map preco-por-produto pedido))

(println (reduce + (map preco-por-produto pedido)))

(defn total-do-pedido
  [pedido]
  (reduce + (map preco-por-produto pedido)))

(println (total-do-pedido pedido))

; THREAD LAST
(defn total-do-pedido
  [pedido]
  (->> pedido
       (map preco-por-produto)
       (reduce +)))

(println (total-do-pedido pedido))

(defn preco-total-do-produto
  [produto]
  (* (:quantidade produto) (:preco produto)))

; THREAD LAST
(defn total-do-pedido
  [pedido]
  (->> pedido
       vals
       (map preco-total-do-produto)
       (reduce +)))

(println (total-do-pedido pedido))

(def pedido {:mochila {:quantidade 2 :preco 80}
             :camiseta {:quantidade 3 :preco 40}
             :chaveiro {:quantidade 1}})

(defn gratuito?
  [[_ produto]]
  (<= (get produto :preco 0) 0))

(println (filter gratuito? pedido))

(defn gratuito?
  [produto]
  (<= (get produto :preco 0) 0))

(println (filter (fn [[_ produto]] (gratuito? produto)) pedido))
(println (filter #(gratuito? (second %)) pedido))

(defn pago?
  [item]
  (not (gratuito? item)))

(println (pago? {:preco 50}))
(println (pago? {:preco 0}))

(println ((comp not gratuito?) {:preco 50}))

(def pago? (comp not gratuito?))

(println (pago? {:preco 50}))
(println (pago? {:preco 0}))

(def clientes [{:nome "Guilherme"
                :certificados ["Clojure" "Java" "Machine Learning"]}
               {:nome "Paulo"
                :certificados ["Java" "Ciência da Computação"]}
               {:nome "Daniela"
                :certificados ["Arquitetura" "Gastronomia"]}])

(println (->> clientes
     (map :certificados)
     (map count)
     (reduce +)))



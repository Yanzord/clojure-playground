(ns basic.vectors)

(defn get-vector-value
  [received-vector, vector-position]
  (get received-vector vector-position))

(defn get-first-vector-value
  [received-vector]
  (first received-vector))

(defn get-last-vector-value
  [received-vector]
  (last received-vector))

(defn count-vector-values
  [received-vector]
  (count received-vector))

(defn add-value-to-vector
  [received-vector, value]
  (conj received-vector value))

(defn add-value-to-specific-index
  [received-vector index value]
  (vec (concat (take index received-vector) 
               [value] 
               (drop index received-vector))))

(defn filter-even-numbers
  [numbers]
  (filter even? numbers))

(defn multiply-numbers-by-two
  [numbers]
  (map #(* 2 %) numbers))

(defn sum-total-numbers
  [numbers]
  (reduce (fn [number result] (+ result number)) 0 numbers))

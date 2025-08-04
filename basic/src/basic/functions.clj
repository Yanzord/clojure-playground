(ns basic.functions)

(defn two
  []
  2)


(defn get-double
  [number name]
  (str "Ola, " name ", o dobro de " number " eh " (* number 2)))
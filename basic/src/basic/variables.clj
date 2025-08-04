(ns basic.variables)

(defn get-double
  [number name]
  (let [double-value (* number 2)]
    (str "Ola, " name ", o dobro de " number " eh " double-value)))
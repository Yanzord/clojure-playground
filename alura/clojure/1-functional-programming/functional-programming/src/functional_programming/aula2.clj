(ns functional-programming.aula2
  (:gen-class))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100, caso contrário retorna o valor bruto."
  [valor-bruto]
  (if (> valor-bruto 100)
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]
    (println "Calculando desconto de" desconto)
    (- valor-bruto desconto))
    valor-bruto))


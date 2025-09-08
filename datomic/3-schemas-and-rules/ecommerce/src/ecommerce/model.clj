(ns ecommerce.model
  (:require [schema.core :as s]))

(defn uuid [] (java.util.UUID/randomUUID))

(def Categoria
  {:categoria/id   java.util.UUID
   :categoria/nome s/Str})

(def Produto
  {(s/optional-key :produto/nome)          s/Str
   (s/optional-key :produto/slug)          s/Str
   (s/optional-key :produto/preco)         BigDecimal
   :produto/id                             java.util.UUID
   (s/optional-key :produto/palavra-chave) [s/Str]
   (s/optional-key :produto/categoria)     Categoria
   (s/optional-key :produto/estoque)       s/Int})

(defn novo-produto
  ([nome slug preco]
   (novo-produto (uuid) nome slug preco))
  ([uuid nome slug preco]
   (novo-produto uuid nome slug preco 0))
  ([uuid nome slug preco estoque]
   ; sera que faz sentido aridade multipla?
   ; pois ai entramos no problema de polimorfismo e multiplos construtores
   ; de outras linguagens
   ; poderiamos optar por um mapa
   ; e se for por mapa, sera que faz sentido um novo-produto?
   {:produto/id      uuid
    :produto/nome    nome
    :produto/slug    slug
    :produto/preco   preco
    :produto/estoque estoque}))

; a "desvantagem" eh o copy e paste nas chaves
; poderiamos optar por um mapa
; e se for por mapa, sera que faz sentido um novo-produto?
;; (s/defn novo-produto :- Produto
;;   [produto]
;;   (if (get produto :produto/id)
;;     produto
;;     (assoc produto :produto/id (uuid))))

(defn nova-categoria
  ([nome]
   (nova-categoria (uuid) nome))
  ([uuid nome]
   {:categoria/id   uuid
    :categoria/nome nome}))


(ns ecommerce.aula2
  (:use clojure.pprint)
  (:require [datomic.client.api :as d]
            [ecommerce.db :as db]
            [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(def db (d/db conn))

(d/q '[:find ?entidade
       :where [?entidade :produto/nome]] db)

; não funciona pois se você quer algo vazio, é só não colocar
;; (let [radio-relogio {:produto/nome "Rádio com relógio" :produto/slug nil}]
;; (d/transact conn {:tx-data [radio-relogio]}))

(let [calculadora {:produto/nome "Calculadora com 4 operações"}]
  (d/transact conn {:tx-data [calculadora]}))

(let [celular-barato (model/novo-produto "Celular Barato" "/celular-barato" 888888.10M)
      resultado (d/transact conn {:tx-data [celular-barato]})]
  (pprint resultado)
  (d/transact conn [[:db/add 13194139533359 :produto/preco 0.1M]])
  (pprint (:tempids resultado)))

(db/apaga-banco)

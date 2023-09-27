(ns curso-1-datomic.ecommerce.aula5
  (:use clojure.pprint)
  (:require [datomic.client.api :as d]
            [ecommerce.db :as db]
            [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(let [computador (model/novo-produto "Computador Novo" "/computador-novo" 2500.10M)
      celular (model/novo-produto "Celular Caro" "/celular" 888888.10M)]
  (pprint (d/transact conn {:tx-data [computador celular]})))

(def banco-no-passado (d/db conn))

(let [calculadora {:produto/nome "Calculadora com 4 operações"}
      celular-barato (model/novo-produto "Celular Barato" "/celular-barato" 0.1M)]
  (pprint (d/transact conn {:tx-data [calculadora celular-barato]})))

(pprint (db/todos-os-produtos (d/db conn)))

(pprint (db/todos-os-produtos (d/as-of (d/db conn) #inst "2023-09-14T02:50:59.092-00:00")))

;; (db/apaga-banco)

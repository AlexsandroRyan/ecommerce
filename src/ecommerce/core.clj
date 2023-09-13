(ns ecommerce.core
  (:use clojure.pprint)
  (:require [datomic.client.api :as d]
            [ecommerce.db :as db]
            [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(let [computador (model/novo-produto "Computador Novo" "/computador_novo" 2500.10M)]
  (d/transact conn {:tx-data [computador]}))

(def db (d/db conn))

(d/q '[:find ?entidade
       :where [?entidade :produto/nome]]
     db)

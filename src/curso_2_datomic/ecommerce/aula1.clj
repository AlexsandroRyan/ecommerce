(ns curso-2-datomic.ecommerce.aula1
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [curso-2-datomic.ecommerce.db :as db]
            [curso-2-datomic.ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(let [computador (model/novo-produto (model/uuid) "Computador Novo" "/computador-novo" 2500.10M)
      celular (model/novo-produto (model/uuid) "Celular Caro" "/celular" 888888.10M)
      calculadora {:produto/nome "Calculadora com 4 operações"}
      celular-barato (model/novo-produto "Celular Barato" "/celular-barato" 0.1M)]
  (pprint (d/transact conn [computador celular calculadora celular-barato])))

(def produtos (db/todos-os-produtos (d/db conn)))
(def primeiro-db-id (-> produtos
                             ffirst
                             :db/id))
(println "O id do primeiro produto é" primeiro-db-id)
(pprint (db/um-produto-por-dbid (d/db conn) primeiro-db-id))

(def primeiro-produto-id (-> produtos
                             ffirst
                             :produto/id))
(println "O id do primeiro produto é" primeiro-produto-id)
(pprint (db/um-produto (d/db conn) primeiro-produto-id))


;; (db/apaga-banco)

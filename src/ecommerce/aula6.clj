(ns ecommerce.aula6
  (:use clojure.pprint)
  (:require [datomic.client.api :as d]
            [ecommerce.db :as db]
            [ecommerce.model :as model]))

(def conn (db/abre-conexao))

(db/cria-schema conn)

(let [computador (model/novo-produto "Computador Novo" "/computador-novo" 2500.10M)
      celular (model/novo-produto "Celular Caro" "/celular" 888888.10M)
      calculadora {:produto/nome "Calculadora com 4 operações"}
      celular-barato (model/novo-produto "Celular Barato" "/celular-barato" 0.1M)]
  (pprint (d/transact conn {:tx-data [computador celular calculadora celular-barato]})))

(db/todos-os-produtos-por-preco-minimo (d/db conn) 1000)

(pprint (count (db/todos-os-produtos-por-preco-minimo (d/db conn) 1000)))

(d/transact conn {:tx-data [[:db/add 74766790688845 :produto/palavra-chave "desktop"]
                            [:db/add 74766790688845 :produto/palavra-chave "computador"]]})

(pprint (db/todos-os-produtos (d/db conn)))

(d/transact conn {:tx-data [[:db/retract 74766790688845 :produto/palavra-chave "desktop"]]})

(pprint (db/todos-os-produtos (d/db conn)))

(d/transact conn {:tx-data [[:db/add 74766790688845 :produto/palavra-chave "monitor preto e branco"]]})

(pprint (db/todos-os-produtos (d/db conn)))

(d/transact conn {:tx-data [[:db/add 74766790688846 :produto/palavra-chave "celular"]
                            [:db/add 74766790688848 :produto/palavra-chave "celular"]]})

(pprint (db/todos-os-produtos (d/db conn)))

(pprint (db/todos-os-produtos-por-palavra-chave (d/db conn) "celular"))
(pprint (db/todos-os-produtos-por-palavra-chave (d/db conn) "computador"))

;; (db/apaga-banco)

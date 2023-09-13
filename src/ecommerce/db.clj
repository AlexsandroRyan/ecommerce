(ns ecommerce.db
  (:require 
   [datomic.client.api :as d]))

(def client (d/client {:server-type :datomic-local
                       :system "dev"}))

(defn abre-conexao 
  []
  (d/create-database client {:db-name "aula1"})
  (d/connect client {:db-name "aula1"}))

(defn apaga-banco
  []
  (d/delete-database client {:db-name "aula1"}))

(def schema 
  [{:db/ident       :produto/nome
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Nome do produto"}
   {:db/ident :produto/slug
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "O caminho para acessar esse produto via http"}
   {:db/ident :produto/preco
    :db/valueType :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc "O preço de um produto com precisão monetária"}])

(defn cria-schema 
  [conn]
  (d/transact conn {:tx-data schema}))

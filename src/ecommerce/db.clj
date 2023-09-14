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

(defn todos-os-produtos [db]
  (d/q '[:find ?entidade
         :where [?entidade :produto/nome]] db))

(defn todos-os-produtos-por-slug
  [db slug-que-estou-procurando]
  (d/q '[:find ?entidade
         :in $ ?slug
         :where [?entidade :produto/slug ?slug]]
       db slug-que-estou-procurando))

(defn todos-os-slugs [db]
  (d/q '[:find ?slug
         :where [?entidade :produto/slug ?slug]]
       db))

(defn todos-os-produtos-por-preco [db]
  (d/q '[:find ?nome ?preco
         :where [?produto :produto/preco ?preco]
                [?produto :produto/nome ?nome]]
       db))

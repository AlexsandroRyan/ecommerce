(ns aula1.core
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :datomic-local
                       :system "dev"}))

(d/create-database client {:db-name "aula1"})

(d/list-databases client {})

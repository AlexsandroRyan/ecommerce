(ns curso-1-datomic.ecommerce.core
  (:use clojure.pprint)
  (:require [datomic.client.api :as d]
            [ecommerce.db :as db]
            [ecommerce.model :as model]))


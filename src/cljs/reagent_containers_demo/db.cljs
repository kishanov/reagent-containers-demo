(ns reagent-containers-demo.db
  (:require [bide.core :as r]
            [reagent-containers-demo.routes :as routes]))



(def router
  (r/router [["/" ::routes/landing]
             ["/layout-containers" ::routes/layout]
             ["/param-containers" ::routes/params]
             ["/stateful-containers" ::routes/stateful]]))


(def default-db
  {::current-route-key ::routes/landing
   ::router            router})

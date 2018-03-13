(ns reagent-containers-demo.subs
  (:require [re-frame.core :as re-frame]
            [reagent-containers-demo.db :as db]))



(re-frame/reg-sub
  ::current-route-key
  (fn [db]
    (::db/current-route-key db)))


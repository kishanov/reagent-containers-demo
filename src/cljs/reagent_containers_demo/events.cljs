(ns reagent-containers-demo.events
  (:require [re-frame.core :as re-frame]
            [reagent-containers-demo.db :as db]
            [reagent-containers-demo.routes :as routes]
            [bide.core :as r]))


(defn on-navigate
  "A function which will be called on each route change."
  [name params query]
  (re-frame/dispatch [::on-navigate name params query]))


(r/start! db/router {:default     ::routes/landing
                     :on-navigate on-navigate})


(re-frame/reg-event-db
  ::initialize-db
  (fn [db]
    (if (::db/current-route-key db)
      db
      db/default-db)))


(re-frame/reg-event-db
  ::on-navigate
  (fn [db [_ name params query]]
    (-> db
        (assoc ::db/current-route-key name))))


(re-frame/reg-event-db
  ::change-route-key
  (fn [db [_ new-route-key]]
    (set! js/window.location.hash (r/resolve db/router new-route-key))
    db))

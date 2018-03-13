(ns reagent-containers-demo.steps.parametrized-containers
  (:require [cljs.repl :as r]))


(defn message [icon-class message-class header description]
  [:div.ui.icon.message
   {:class message-class}
   [:i.icon
    {:class icon-class}]
   [:div.content
    [:div.header header]
    [:div description]]])


(def error-message (partial message "warning sign" "negative"))
(def success-message (partial message "check circle" "positive"))
(def loading-message (partial message "notched circle loading" "default"))


(defn error-message-usage []
  [error-message
   "Oops"
   [:div "Something went wrong. Please, "
    [:a {:href "#"} "contact administrator"]]])


[error-message
 "Oops"
 [:div "Something went wrong. Please, "
  [:a {:href "#"} "contact administrator"]]]

(defn success-message-usage []
  [success-message
   "Submitted successfully"
   [:p "You did it! " [:i.thumbs.up.icon]]])


(defn loading-message-usage []
  [loading-message
   "Wait a sec"
   [:div.ui.list
    [:div.item "Cleaning cache data"]
    [:div.item "Re-fetching images"]]])


(defn main-panel []
  [:div.ui.internally.celled.grid
   [:div.two.column.row
    [:div.column
     [:h4.ui.dividing.header "Parametrized containers"]
     [error-message-usage]
     [success-message-usage]
     [loading-message-usage]]

    [:div.column
     [:h4.ui.dividing.header "Source code"]
     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source message))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source error-message))
       (with-out-str (r/source success-message))
       (with-out-str (r/source loading-message))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source error-message-usage))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source success-message-usage))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source loading-message-usage))]]]]])




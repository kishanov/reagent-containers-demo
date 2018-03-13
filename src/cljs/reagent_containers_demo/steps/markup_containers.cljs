(ns reagent-containers-demo.steps.markup-containers
  (:require [cljs.repl :as r]))


(defn sample-component []
  [:div
   [:p "Lorem ipsum"]
   [:button.ui.mini.basic.button "Don't click"]])


(defn basic-container [view-component]
  [:div view-component])


(defn basic-container-usage []
  [basic-container
   [sample-component]])


(defn main-panel []
  [:div.ui.internally.celled.grid
   [:div.two.column.row
    [:div.column
     [:h4.ui.dividing.header "Basic container"]
     [basic-container-usage]]
    [:div.column
     [:h4.ui.dividing.header "Source code"]
     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source sample-component))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source basic-container))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source basic-container-usage))]]]]])

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



(defn basic-container-panel []
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



(defn centered-column-container [view-component]
  [:div.ui.grid
   {:style {:background "purple"}}
   [:div.two.column.centered.row
    [:div.column
     {:style {:background "cyan"}}
     view-component]]])


(defn centered-column-container-usage []
  [centered-column-container
   [sample-component]])


(defn grid-container-panel []
  [:div.ui.internally.celled.grid
   [:div.two.column.row
    [:div.column
     [:h4.ui.dividing.header "Centered column container"]
     [centered-column-container-usage]]
    [:div.column
     [:h4.ui.dividing.header "Source code"]
     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source sample-component))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source centered-column-container))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source centered-column-container-usage))]]]]])

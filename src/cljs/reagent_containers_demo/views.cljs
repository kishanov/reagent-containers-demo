(ns reagent-containers-demo.views
  (:require [re-frame.core :as re-frame]
            [reagent-containers-demo.routes :as routes]
            [reagent-containers-demo.events :as events]
            [reagent-containers-demo.subs :as subs]

            [reagent-containers-demo.steps.markup-containers :as markup-step]))



(defn landing []
  [:div.ui.internally.celled.grid
   [:div.two.column.row
    [:div.column
     [:h4.ui.dividing.header "About"]]]])



(defn steps [current-route-key]
  (->> (list [::routes/landing "Introduction" "Show very basic container"]
             [::routes/layout "Layout containers" "Use containers to create reusable layouts"])
       (map (fn [[route-key title desc]]
              [:a.step
               {:class    (when (= current-route-key route-key) "active")
                :on-click #(re-frame/dispatch [::events/change-route-key route-key])}
               [:div.content
                [:div.title title]
                [:div.description
                 {:style {:max-width "10em"}}
                 desc]]]))

       (into [:div.ui.vertical.ordered.steps])))



(defn main-panel []
  (let [route-key @(re-frame/subscribe [::subs/current-route-key])]
    [:div.ui.container
     {:style {:margin-top "5em"}}
     [:h1.ui.header "Using Containers with Reagent and re-frame"]
     [:div.ui.grid
      [:div.two.column.row
       [:div.four.wide.column
        [steps route-key]]
       [:div.twelve.wide.column
        ^{:key route-key}
        [:div.section
         (condp = route-key
           ::routes/layout [markup-step/grid-container-panel]
           [markup-step/basic-container-panel])]]]]]))

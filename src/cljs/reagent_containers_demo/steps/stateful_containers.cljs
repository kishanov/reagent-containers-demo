(ns reagent-containers-demo.steps.stateful-containers
  (:require [reagent.core :as reagent]
            [cljs.repl :as r]))


(defn nav-tabs [menu-class tabs-def]
  (let [active-tab (reagent/atom (:key (first tabs-def)))]
    (fn []
      [:div
       (into [:div.ui.menu
              {:class menu-class}]
             (map (fn [{:keys [key label]}]
                    [:a.item
                     {:class    (when (= @active-tab key)
                                  "active")
                      :on-click #(reset! active-tab key)}
                     label])
                  tabs-def))
       ^{:key @active-tab}
       [:div (->> tabs-def
                  (filter #(= @active-tab (:key %)))
                  (first)
                  :component)]])))


(defn nav-tabs-usage []
  [nav-tabs
   "tabular"
   (list {:key       :home
          :label     "Home"
          :component [:div "Home panel"]}
         {:key       :messages
          :label     "Messages"
          :component [:p "You have " [:strong 0] " unread messages!"]}
         {:key       :friends
          :label     "Friends"
          :component [:div.ui.list
                      [:div.item "Friend 1"]
                      [:div.item "Friend 2"]]})])


(defn nav-tabs-usage-nested []
  [nav-tabs
   "tabular"
   (list {:key       :home
          :label     "Home"
          :component [:div "Home panel"]}
         {:key       :messages
          :label     "Messages"
          :component [nav-tabs
                      "secondary"
                      (list {:key       :inbox
                             :label     "Inbox"
                             :component [:div "No new messages"]}
                            {:key       :spam
                             :label     "Spam"
                             :component [:strong "Enlarge your EGO"]})]}
         {:key       :friends
          :label     "Friends"
          :component [:div.ui.list
                      [:div.item "Friend 1"]
                      [:div.item "Friend 2"]]})])



(defn main-panel []
  [:div.ui.internally.celled.grid
   [:div.two.column.row
    [:div.column
     [:h4.ui.dividing.header "Tabs"]
     [nav-tabs-usage]

     [:div.ui.hidden.divider]

     [:h4.ui.dividing.header "Nested Tabs"]

     [nav-tabs-usage-nested]]

    [:div.column
     [:h4.ui.dividing.header "Source code"]
     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source nav-tabs))]]


     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source nav-tabs-usage))]]

     [:pre
      [:code {:class "clojure"}
       (with-out-str (r/source nav-tabs-usage-nested))]]]]])

(ns zipurl.views.welcome
  (:require [zipurl.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))


(defpage "/" []
  (common/layout
   [:p "Welcome to ZIP URL"]))

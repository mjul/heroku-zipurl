(ns zipurl.views.welcome
  (:import [java.io ByteArrayOutputStream]
           [java.util.zip ZipOutputStream ZipEntry])
  (:require [zipurl.views.common :as common]
            [noir.content.getting-started]
            [clj-http.client :as client])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [clojure.java.io :only [reader writer copy file input-stream]]
        ))


(defpage "/" []
  (common/layout
   [:h1 "Choose a URL to ZIP and download"]
   [:form {:action "/download/" :method "GET"}
    [:input {:name "url" :type "text" :id "url"}]
    [:input {:type "Submit" :value "Download as zip"}]]
   ))


(defn download [url]
  (let [resp (client/get url {:as :byte-array})]
    (when (= 200 (:status resp))
      (:body resp))))


(defn create-zip [data]
  (let [bos (ByteArrayOutputStream.)
        zipfile (ZipOutputStream. bos)
        zipentry (ZipEntry. "content.dat")]
    (.setSize zipentry (count data))
    (doto zipfile
      (.putNextEntry zipentry)
      (.write data 0 (count data))
      (.closeEntry)
      (.close))
    (.toByteArray bos)))


(defpage "/download/" {:keys [url]}
  (if-let [bytes (download url)]
    {:header {:content-type "application/zip"
              :content-length (count bytes)}
     :body (input-stream (create-zip bytes))}
    (common/layout
     [:h1 "Download failed."])))

    


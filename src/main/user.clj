(ns user
  (:require
   [nrepl.server]
   [nrepl.transport]
   [shadow.cljs.devtools.api :as shadow.api]
   [shadow.cljs.devtools.server :as shadow.server]
   [shadow.cljs.devtools.server.nrepl]))

(def ^:private doc-str
  (str "Docs: " \newline
       "(user/start-watches)                 Start shadow build server." \newline
       "(user/repl-connect shadow-build-id)  Connect to a running build instance. :cljs/quit to exit " \newline))

(defn- greeting
  [transport]
  (nrepl.transport/send transport {:out doc-str}))

(defn -start-nrepl
  [_]
  (let [{:keys [port]} (nrepl.server/start-server
                        :greeting-fn greeting
                        :handler (nrepl.server/default-handler 
                                  #'shadow.cljs.devtools.server.nrepl/middleware))]
    (spit ".nrepl-port" port)
    (println "Connect via ./.nrepl-port, current port is" port)
    (println doc-str)))

(defn start-watches
  []
  {:server (shadow.server/start!)
   :electron/main (shadow.api/watch :electron/main)
   :electron/render (shadow.api/watch :electron/render)})

(defn repl-connect
  [shadow-build-id]
  ;; change the repl mode in your editor to cljs
  (println [(shadow.api/nrepl-select shadow-build-id)
            (shadow.api/repl shadow-build-id)]))

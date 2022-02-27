(ns dev.freeformsoftware.electron-performance.main-proc
  (:require
   ["electron" :refer [app BrowserWindow ipcMain ipcRenderer]]
   ["path" :as path]))

(defonce main-browser-window (atom nil))

(defn create-window
  []
  (reset! main-browser-window
          (doto (BrowserWindow. #js {:width 800
                                     :height 600
                                     :webPreferences #js {:preload (path/join js/__dirname "preload.js")}})
            (.loadFile "resources/electron/render/assets/index.html"))))

(defn main
  "Initial entry point of the app."
  []
  (js/console.log "EP MAIN")

  (doto (.whenReady app)
    (.then #(create-window))))

(defn reload
  "Run after hot code reload"
  []
  (js/console.log "EP RELOAD"))


(comment 
 (.on ipcMain
      "echo-to-console" js/console.log)
 (.send (.-webContents @main-browser-window)
        "echo-to-devtools" "hello-world"))

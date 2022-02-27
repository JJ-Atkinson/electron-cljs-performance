(ns dev.freeformsoftware.electron-performance.render-proc)

(defn main [])

(defn reload [])

(comment 
 (let [ipcRender (.. js/window -mainProc -ipcRenderObj)]
   (.send ipcRender
          "echo-to-console" "hello-world"))

 (let [ipcRender (.. js/window -mainProc -ipcRenderObj)]
   (.on ipcRender
          "echo-to-devtools" js/console.log)))

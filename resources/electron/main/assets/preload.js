const { contextBridge, ipcRenderer } = require('electron')

contextBridge.exposeInMainWorld('mainProc', {
    ipcRenderObj: ipcRenderer,
    proto: ipcRenderer.on
})
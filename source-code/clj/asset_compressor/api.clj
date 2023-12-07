
(ns asset-compressor.api
    (:require [asset-compressor.css-compressor :as css-compressor]
              [asset-compressor.engine         :as engine]
              [asset-compressor.js-compressor  :as js-compressor]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (asset-compressor.css-compressor)
(def compress-css  css-compressor/compress-css)
(def compress-css! css-compressor/compress-css!)

; @redirect (asset-compressor.engine)
(def compress-assets! engine/compress-assets!)

; @redirect (asset-compressor.js-compressor)
; (def compress-js  js-compressor/compress-js)
; (def compress-js! js-compressor/compress-js!)

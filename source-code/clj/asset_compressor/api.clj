
(ns asset-compressor.api
    (:require [asset-compressor.css-compressor  :as css-compressor]
              [asset-compressor.engine          :as engine]
              [asset-compressor.html-compressor :as html-compressor]
              [asset-compressor.js-compressor   :as js-compressor]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (asset-compressor.css-compressor/*)
(def compress-css        css-compressor/compress-css)
(def compress-css-files! css-compressor/compress-css-files!)

; @redirect (asset-compressor.engine/*)
(def compress-assets! engine/compress-assets!)

; @redirect (asset-compressor.html-compressor/*)
(def compress-html        html-compressor/compress-html)
(def compress-html-files! html-compressor/compress-html-files!)

; @redirect (asset-compressor.js-compressor/*)
(def compress-js        js-compressor/compress-js)
(def compress-js-files! js-compressor/compress-js-files!)

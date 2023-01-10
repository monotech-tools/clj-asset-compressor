
(ns asset-compressor.js-compressor
    (:require [asset-compressor.engine :as engine]
              [string.api              :as string]
              [syntax.api              :as syntax]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-js
  ; @param (string) file-content
  ;
  ; @usage
  ; (compress-js "...")
  ;
  ; @example
  ; (compress-js "...")
  ; =>
  ; "..."
  ;
  ; @return (string)
  [file-content])
  ; TODO

(defn compress-js!
  ; @param (string) output-path
  ; @param (strings in vector) resources
  ;
  ; @usage
  ; (compress-css! "my-code.min.js" ["my-directory" "my-code.js"])
  [output-path resources]
  (engine/compress-assets! output-path resources compress-js))

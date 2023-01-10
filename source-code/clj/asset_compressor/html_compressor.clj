
(ns asset-compressor.html-compressor
    (:require [asset-compressor.engine :as engine]
              [string.api              :as string]
              [syntax.api              :as syntax]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-html
  ; @param (string) file-content
  ;
  ; @usage
  ; (compress-html "...")
  ;
  ; @example
  ; (compress-html "...")
  ; =>
  ; "..."
  ;
  ; @return (string)
  [file-content])
  ; TODO

(defn compress-html!
  ; @param (string) output-path
  ; @param (strings in vector) resources
  ;
  ; @usage
  ; (compress-css! "my-page.min.html" ["my-directory" "my-page.html"])
  [output-path resources]
  (engine/compress-assets! output-path resources compress-html))

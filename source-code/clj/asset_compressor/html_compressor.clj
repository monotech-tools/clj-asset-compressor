
(ns asset-compressor.html-compressor
    (:require [asset-compressor.engine :as engine]
              [string.api              :as string]
              [syntax.api              :as syntax]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-html
  ; @description
  ; Returns the given file-content compressed.
  ;
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
  ; @description
  ; Compresses all the given resources into the output file.
  ; If any resource is a directory all of its files will be compressed into the output file.
  ;
  ; @param (string) output-path
  ; @param (strings in vector) resources
  ;
  ; @usage
  ; (compress-css! "my-page.min.html" ["my-directory" "my-page.html"])
  [output-path resources]
  (engine/compress-assets! output-path resources compress-html))

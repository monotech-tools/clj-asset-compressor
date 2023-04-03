
(ns asset-compressor.js-compressor
    (:require [asset-compressor.engine :as engine]
              [string.api              :as string]
              [syntax.api              :as syntax]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-js
  ; @description
  ; Returns the given file-content compressed.
  ;
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
  ; @description
  ; Compresses all the given resources into the output file.
  ; If any resource is a directory all of its files will be compressed into the output file.
  ;
  ; @param (string) output-path
  ; @param (strings in vector) resources
  ;
  ; @usage
  ; (compress-css! "my-code.min.js" ["my-directory" "my-code.js"])
  [output-path resources]
  (engine/compress-assets! output-path resources compress-js))

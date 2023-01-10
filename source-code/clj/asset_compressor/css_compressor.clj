
(ns asset-compressor.css-compressor
    (:require [asset-compressor.engine :as engine]
              [string.api              :as string]
              [syntax.api              :as syntax]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-css
  ; @param (string) file-content
  ;
  ; @usage
  ; (compress-css "body {\n color: blue; }")
  ;
  ; @example
  ; (compress-css "body {\n color: blue; }")
  ; =>
  ; "body{color:blue}"
  ;
  ; @return (string)
  [file-content]
  (letfn [(f [result [a b]] (string/replace-part result a b))]
         (as-> file-content % (syntax/remove-comments % "/*" "*/")
                              (reduce f % [["\n" ""]
                                           ["  " ""]
                                           [" (" "("]
                                           [" )" ")"]
                                           [" {" "{"]
                                           [" }" "}"]
                                           [": " ":"]
                                           [";}" "}"]]))))

(defn compress-css!
  ; @param (string) output-path
  ; @param (strings in vector) resources
  ;
  ; @usage
  ; (compress-css! "my-style.min.css" ["my-directory" "my-style.css"])
  [output-path resources]
  (engine/compress-assets! output-path resources compress-css))

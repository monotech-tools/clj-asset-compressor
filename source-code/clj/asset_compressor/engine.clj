
(ns asset-compressor.engine
    (:require [asset-compressor.tests :as tests]
              [io.api                 :as io]
              [validator.api          :as v]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-assets!
  ; @description
  ; - Compresses files (that match the given filename pattern) with the given 'compressor-f' function.
  ; - Returns the compressed output.
  ;
  ; @param (map) options
  ; {:compressor-f (function)
  ;  :filename-pattern (regex pattern)
  ;  :output-path (string)
  ;  :source-paths (strings in vector)}
  ;
  ; @usage
  ; (defn my-compressor-f [file-content] ...)
  ; (compress-assets! {:compressor-f     my-compressor-f
  ;                    :filename-pattern #".*\.css"
  ;                    :output-path      "my-style.min.css"
  ;                    :source-paths     ["my-directory"]})
  ;
  ; @return (string)
  [{:keys [compressor-f filename-pattern output-path source-paths] :as options}]
  (if (v/valid? options tests/OPTIONS-TEST {:prefix "options"})
      (letfn [; @param (string) filepath
              ;
              ; @usage
              ; (f0 "my-style.css")
              (f0 [filepath] (if-let [file-content (io/read-file filepath {:warn? true})]
                                     (try (compressor-f file-content)
                                          (catch Exception e nil))))

              ; @param (string) result
              ; @param (string) filepath
              ;
              ; @usage
              ; (f1 "..." "my-style.css")
              (f1 [result filepath]
                  (println "Compressing asset:" filepath)
                  (str result (f0 filepath) "\n"))

              ; @param (string) result
              ; @param (string) source-path
              ;
              ; @usage
              ; (f2 "..." "my-directory")
              (f2 [result source-path]
                  (let [file-list (io/search-files source-path filename-pattern)]
                       (reduce f1 result file-list)))

              ; @param (strings in vector) source-paths
              ;
              ; @usage
              ; (f3 ["my-directory"])
              (f3 [source-paths] (reduce f2 "" source-paths))]

             ; ...
             (println "Compressing assets to output:" output-path)
             (let [output-content (f3 source-paths)]
                  (io/write-file! output-path output-content {:create? true :warn? true :return? true})))))

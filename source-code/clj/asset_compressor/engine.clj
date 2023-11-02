
(ns asset-compressor.engine
    (:require [io.api :as io]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-assets!
  ; @description
  ; - Compresses all the resources found by the given 'resources' vector into the output file.
  ; - If a resource is a directory all of its files will be compressed into the output file.
  ;
  ; @param (string) output-path
  ; @param (strings in vector) resources
  ; @param (function) compressor-f
  ;
  ; @usage
  ; (defn my-compressor-f [file-content] ...)
  ; (compress-assets! "my-style.min.css" ["my-directory" "my-style.css"] my-compressor-f)
  [output-path resources compressor-f]
  (letfn [
          ; @param (string) resource-path
          ;
          ; @usage
          ; (f0 "my-style.css")
          (f0 [resource-path] (if-let [resource-content (io/read-file resource-path {:warn? true})]
                                      (compressor-f resource-content)))

          ; @param (string) result
          ; @param (string) resource-path
          ;
          ; @usage
          ; (f1 "..." "my-style.css")
          (f1 [result resource-path] (println "Compressing asset:" resource-path)
                                     (str result (f0 resource-path) "\n"))

          ; @param (string) result
          ; @param (string) resource
          ;
          ; @usage
          ; (f2 "..." "my-directory")
          ;
          ; @usage
          ; (f2 "..." "my-style.css")
          (f2 [result resource]
              (cond (io/directory? resource) (str result (-> resource io/all-file-list f3))
                    (io/file?      resource) (f1  result resource)
                    :return result))

          ; @param (strings in vector) resources
          ;
          ; @usage
          ; (f3 ["my-directory" "my-style.css"])
          (f3 [resources] (reduce f2 "" resources))]

         ; ...
         (println "Compressing assets to output:" output-path)
         (let [output-content (f3 resources)]
              (io/write-file! output-path output-content {:create? true :warn? true}))))

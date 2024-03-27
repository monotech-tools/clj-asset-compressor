
(ns asset-compressor.tests
    (:require [fruits.regex.api  :as regex]
              [fruits.string.api :as string]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @ignore
;
; @links
; [cljc-validator](https://github.com/mt-utilities/cljc-validator)
;
; @constant (map)
(def OPTIONS-TEST
     {:compressor-f     {:f* fn?                                       :e* ":compressor-f must be a function!"}
      :filename-pattern {:f* regex/pattern?                            :e* ":filename-pattern must be a regex pattern!"}
      :output-path      {:f* string? :not* empty?                      :e* ":output-path must be a nonempty string!"}
      :source-paths     {:and* [vector? #(every? string/not-empty? %)] :e* ":source-paths must be a vector with nonempty string items!"}})

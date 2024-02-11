
(ns asset-compressor.css-compressor
    (:require [asset-compressor.engine :as engine]
              [fruits.noop.api         :refer [none return]]
              [fruits.string.api       :as string]
              [syntax-reader.api       :as syntax-reader]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-css
  ; @description
  ; Returns the given 'n' CSS string compressed.
  ;
  ; @param (string) n
  ;
  ; @usage
  ; (compress-css "body {\n color: blue; }")
  ; =>
  ; "body{color:blue}"
  ;
  ; @return (string)
  [n]
  ; 0.)
  ; Comments:
  ; - E.g., "body { backgound-color: red } /* My comment */"
  ; - Commented parts can be removed.
  ; - Interpreter must be disabled while processing (to prevent misreading syntax).
  ;
  ; 1.)
  ; Double quoted strings:
  ; - E.g., "background-image: url( "my-image.png" )"
  ; - Surronding whitespaces can be removed.
  ; - Interpreter must be disabled while processing (quoted parts are not compressed).
  ;
  ; 2.)
  ; Single quoted strings:
  ; - E.g., "background-image: url( 'my-image.png' )"
  ; - Surronding whitespaces can be removed.
  ; - Interpreter must be disabled while processing (quoted parts are not compressed).
  ;
  ; 3-6.)
  ; Opening / closing parenthesis and brace characters:
  ; - Surronding whitespaces can be removed.
  ; - Semicolons preceding a closing brace can be removed.
  ;
  ; 7-8.)
  ; Opening / closing bracket characters:
  ; - Surronding whitespaces around brackets CANNOT be removed!
  ;
  ; 9-11.)
  ; Colon, semicolon, comma characters:
  ; - Surronding whitespaces can be removed.
  ;
  ; 12.)
  ; Multiple whitespaces:
  ; - Can be replaced with a single whitespace.
  ;
  ; 13.)
  ; Newlines
  ; - Can be removed.
  (-> n string/trim
      (syntax-reader/update-tags [[:t0  #"\s*\/\*" #"\*\/\s*" {:accepted-children [] :update-f none}]
                                  [:t1  #"\s*\""   #"\"\s*"   {:accepted-children [] :update-f string/trim}]
                                  [:t2  #"\s*\'"   #"\'\s*"   {:accepted-children [] :update-f string/trim}]
                                  [:t3  #"[\s]*\([\s]*"                             {:update-f (fn [_] "(")}]
                                  [:t4  #"[\s]*\)[\s]*"                             {:update-f (fn [_] ")")}]
                                  [:t5  #"[\s]*\{[\s]*"                             {:update-f (fn [_] "{")}]
                                  [:t6  #"[\s\;]*\}[\s]*"                           {:update-f (fn [_] "}")}]
                                  [:t7  #"[\s]*\[[\s]*"                             {:update-f return}]
                                  [:t8  #"[\s]*\][\s]*"                             {:update-f return}]
                                  [:t9  #"[\s]*\:[\s]*"                             {:update-f (fn [_] ":")}]
                                  [:t10 #"[\s]*\;[\s]*"                             {:update-f (fn [_] ";")}]
                                  [:t11 #"[\s]*\,[\s]*"                             {:update-f (fn [_] ",")}]
                                  [:t12 #"\s{2,}"                                   {:update-f (fn [_] " ")}]
                                  [:t13 #"\n"                                       {:update-f none}]])))

(defn compress-css-files!
  ; @description
  ; - Compresses CSS files (that match the given filename pattern) within the given source paths.
  ; - Returns the compressed output.
  ;
  ; @param (map) options
  ; {:compressor-f (function)(opt)
  ;   Default: compress-css
  ;  :filename-pattern (regex pattern)(opt)
  ;   Default: #".*\.css"
  ;  :output-path (string)
  ;  :source-paths (strings in vector)}
  ;
  ; @usage
  ; (compress-css-files! {:output-path "my-style.min.css"
  ;                       :source-paths ["my-directory"]})
  [{:keys [compressor-f filename-pattern output-path source-paths] :or {compressor-f compress-css filename-pattern #".*\.css"}}]
  (engine/compress-assets! {:compressor-f compressor-f :filename-pattern filename-pattern :output-path output-path :source-paths source-paths}))

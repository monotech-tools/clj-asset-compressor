
(ns asset-compressor.html-compressor
    (:require [asset-compressor.engine :as engine]
              [fruits.noop.api         :refer [none return]]
              [fruits.string.api       :as string]
              [syntax-reader.api       :as syntax-reader]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compress-html
  ; @description
  ; Returns the given 'n' HTML string compressed.
  ;
  ; @param (string) n
  ;
  ; @usage
  ; (compress-html "<div>  <img src="my-image.png" />  </div>")
  ; =>
  ; "<div><img src="my-image.png"/></div>"
  ;
  ; @return (string)
  [n]
  ; 0.)
  ; Comments:
  ; - E.g., "<div><!-- My comment --></div>"
  ; - Commented parts can be removed.
  ; - Interpreter must be disabled while processing (to prevent misreading syntax).
  ;
  ; 1.)
  ; Double quoted strings:
  ; - E.g., "<div my-attribute="My value"></div>"
  ; - Surronding whitespaces not allowed by standard.
  ; - Interpreter must be disabled while processing (quoted parts are not compressed).
  ;
  ; 2.)
  ; Single quoted strings:
  ; - E.g., "<div my-attribute='My value'></div>"
  ; - Surronding whitespaces not allowed by standard.
  ; - Interpreter must be disabled while processing (quoted parts are not compressed).
  ;
  ; 3.)
  ; Equal sign
  ; - E.g., "<div my-attribute="My value"></div>"
  ; - Surronding whitespaces not allowed by standard.
  ;
  ; 4-6.)
  ; Tag opening and closing characters (LT, GT):
  ; - Surronding whitespaces can be removed.
  ; - Semicolons preceding a closing brace can be removed.
  ;
  ; 7.)
  ; Multiple whitespaces:
  ; - Can be replaced with a single whitespace.
  ;
  ; 8.)
  ; Newlines
  ; - Can be removed.
  (-> n string/trim
      (syntax-reader/update-tags [[:t0 #"\s*\<\!\-\-" #"\-\-\>\s*" {:accepted-children [] :update-f none}]
                                  [:t1 #"\""          #"\""        {:accepted-children [] :update-f return}]
                                  [:t2 #"\'"          #"\'"        {:accepted-children [] :update-f return}]
                                  [:t3 #"="                                              {:update-f return}]
                                  [:t4 #"[\s]*\<[\s]*"                                   {:update-f (fn [_] "<")}]
                                  [:t5 #"[\s]*\>[\s]*"                                   {:update-f (fn [_] ">")}]
                                  [:t6 #"[\s]*\/\>[\s]*"                                 {:update-f (fn [_] "/>")}]
                                  [:t7 #"\s{2,}"                                         {:update-f (fn [_] " ")}]
                                  [:t8 #"\n"                                             {:update-f none}]])))

(defn compress-html-files!
  ; @description
  ; - Compresses HTML files within the given source paths that match the given filename pattern.
  ; - Returns the compressed output.
  ;
  ; @param (map) options
  ; {:compressor-f (function)(opt)
  ;   Default: compress-html
  ;  :filename-pattern (regex pattern)(opt)
  ;   Default: #".*\.html"
  ;  :output-path (string)
  ;  :source-paths (strings in vector)}
  ;
  ; @usage
  ; (compress-html-files! {:output-path "my-page.min.html"
  ;                        :source-paths ["my-directory"]})
  [{:keys [compressor-f filename-pattern output-path source-paths] :or {compressor-f compress-html filename-pattern #".*\.html"}}]
  (engine/compress-assets! {:compressor-f compressor-f :filename-pattern filename-pattern :output-path output-path :source-paths source-paths}))

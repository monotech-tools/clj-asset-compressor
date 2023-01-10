
# asset-compressor.api Clojure namespace

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > asset-compressor.api

### Index

- [compress-assets!](#compress-assets)

- [compress-css](#compress-css)

- [compress-css!](#compress-css)

- [compress-html](#compress-html)

- [compress-html!](#compress-html)

- [compress-js](#compress-js)

- [compress-js!](#compress-js)

### compress-assets!

```
@param (string) output-path
@param (strings in vector) resources
@param (function) compressor-f
```

```
@usage
(defn my-compressor-f [file-content] ...)
(compress-assets! "my-style.min.css" ["my-directory" "my-style.css"] my-compressor-f)
```

<details>
<summary>Source code</summary>

```
(defn compress-assets!
  [output-path resources compressor-f]
  (letfn [
          (f0 [resource-path] (if-let [resource-content (io/read-file resource-path {:warn? true})]
                                      (compressor-f resource-content)))

          (f1 [result resource-path] (println "Compressing asset:" resource-path)
                                     (str result (f0 resource-path) "\n"))

          (f2 [result resource]
              (cond (io/directory? resource) (-> resource io/all-file-list f3)
                    (io/file?      resource) (f1 result resource)
                    :return        result))

          (f3 [resources] (reduce f2 "" resources))]

         (println "Compressing assets to output:" output-path)
         (let [output-content (f3 resources)]
              (io/write-file! output-path output-content {:create? true :warn? true}))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [asset-compressor.api :refer [compress-assets!]]))

(asset-compressor.api/compress-assets! ...)
(compress-assets!                      ...)
```

</details>

---

### compress-css

```
@param (string) file-content
```

```
@usage
(compress-css "body {\n color: blue; }")
```

```
@example
(compress-css "body {\n color: blue; }")
=>
"body{color:blue}"
```

```
@return (string)
```

<details>
<summary>Source code</summary>

```
(defn compress-css
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
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [asset-compressor.api :refer [compress-css]]))

(asset-compressor.api/compress-css ...)
(compress-css                      ...)
```

</details>

---

### compress-css!

```
@param (string) output-path
@param (strings in vector) resources
```

```
@usage
(compress-css! "my-style.min.css" ["my-directory" "my-style.css"])
```

<details>
<summary>Source code</summary>

```
(defn compress-css!
  [output-path resources]
  (engine/compress-assets! output-path resources compress-css))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [asset-compressor.api :refer [compress-css!]]))

(asset-compressor.api/compress-css! ...)
(compress-css!                      ...)
```

</details>

---

### compress-html

```
@param (string) file-content
```

```
@usage
(compress-html "...")
```

```
@example
(compress-html "...")
=>
"..."
```

```
@return (string)
```

<details>
<summary>Source code</summary>

```
(defn compress-html
  [file-content])
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [asset-compressor.api :refer [compress-html]]))

(asset-compressor.api/compress-html ...)
(compress-html                      ...)
```

</details>

---

### compress-html!

```
@param (string) output-path
@param (strings in vector) resources
```

```
@usage
(compress-css! "my-page.min.html" ["my-directory" "my-page.html"])
```

<details>
<summary>Source code</summary>

```
(defn compress-html!
  [output-path resources]
  (engine/compress-assets! output-path resources compress-html))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [asset-compressor.api :refer [compress-html!]]))

(asset-compressor.api/compress-html! ...)
(compress-html!                      ...)
```

</details>

---

### compress-js

```
@param (string) file-content
```

```
@usage
(compress-js "...")
```

```
@example
(compress-js "...")
=>
"..."
```

```
@return (string)
```

<details>
<summary>Source code</summary>

```
(defn compress-js
  [file-content])
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [asset-compressor.api :refer [compress-js]]))

(asset-compressor.api/compress-js ...)
(compress-js                      ...)
```

</details>

---

### compress-js!

```
@param (string) output-path
@param (strings in vector) resources
```

```
@usage
(compress-css! "my-code.min.js" ["my-directory" "my-code.js"])
```

<details>
<summary>Source code</summary>

```
(defn compress-js!
  [output-path resources]
  (engine/compress-assets! output-path resources compress-js))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [asset-compressor.api :refer [compress-js!]]))

(asset-compressor.api/compress-js! ...)
(compress-js!                      ...)
```

</details>

---

This documentation is generated by the [docs-api](https://github.com/bithandshake/docs-api) engine


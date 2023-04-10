
# clj-asset-compressor

### Overview

The <strong>clj-asset-compressor</strong> is a CSS / HTML / JS minifier for Clojure/ClojureScript
projects.

> The HTML and JS compressors are missing. This library only compresses CSS files now!

### deps.edn

```
{:deps {bithandshake/clj-asset-compressor {:git/url "https://github.com/bithandshake/clj-asset-compressor"
                                           :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/clj-asset-compressor/tree/release).

### Documentation

The <strong>clj-asset-compressor</strong> functional documentation is [available here](documentation/COVER.md).

### Changelog

You can track the changes of the <strong>clj-asset-compressor</strong> library [here](CHANGES.md).

### Index

- [How to compress a CSS file?](#how-to-compress-a-css-file)

# Usage

### How to compress a CSS file?

The [`asset-compressor.api/compress-css!`](documentation/clj/asset-compressor/API.md/#compress-css)
function compresses the given resources into an output file.

If any resource is a directory all of its files will be compressed into the output file.

```
(compress-css! "my-style.min.css" ["my-directory" "my-style.css"])
```

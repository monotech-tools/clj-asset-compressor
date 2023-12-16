
# clj-asset-compressor

### Overview

The <strong>clj-asset-compressor</strong> is a CSS / HTML / JS file minifier for Clojure projects.

> The HTML and JS compressors are unfinished at the moment. This library only compresses CSS files!

### deps.edn

```
{:deps {monotech-tools/clj-asset-compressor {:git/url "https://github.com/monotech-tools/clj-asset-compressor"
                                             :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/monotech-tools/clj-asset-compressor/tree/release).

### Documentation

The <strong>clj-asset-compressor</strong> functional documentation is [available here](https://mt-devtools.github.io/clj-asset-compressor).

### Changelog

You can track the changes of the <strong>clj-asset-compressor</strong> library [here](CHANGES.md).

# Usage

> Some parameters of the following functions and some further functions are not discussed in this file.
  To learn more about the available functionality, check out the [functional documentation](documentation/COVER.md)!

### Index

- [How to compress a CSS file?](#how-to-compress-a-css-file)

### How to compress a CSS file?

The [`asset-compressor.api/compress-css!`](documentation/clj/asset-compressor/API.md/#compress-css)
function compresses the given resources into an output file.

If any resource is a directory all of its files will be compressed into the output file.

```
(compress-css! "my-style.min.css" ["my-directory" "my-style.css"])
```

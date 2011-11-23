# Heroku Zip URL

Given a URL, it will HTTP GET that URL, stuff its contents into a ZIP file and return that.

It allows you to go around corporate firewalls filtering out certain content types, such as Emacs installers or other executables.

Written in Clojure with the noir library (as in pinot noir) to run on the Heroku Clojure cloud.

If you plan on using it heavily, consider rewriting it for streaming the data through the web app rather than downloading, zipping and then emitting.

## Usage

```bash
lein deps
lein run
```

By default it starts up on http://localhost:8080/

## License

Copyright (C) 2011 Martin Jul

Distributed under the Eclipse Public License, the same as Clojure.


# 4Clojure Solutions

This repo contains my solutions to the
[4clojure](http://www.4clojure.com/) problems, and is a work in
progress.

They are in `src/fourclojure/core.clj` for no good reason other than
that's a file that was created with `lein new`. The structure of the
solutions includes the description of the problem in a `deftest` and
each of the examples within an `is` in that test. At the end of the file
is a call to `run-tests` so I can just run `:%Eval` in vim-fireplace and
it all runs.

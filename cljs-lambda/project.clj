(defproject io.nervous/cljs-lambda "0.3.3"
  :description "Clojurescript AWS Lambda utilities"
  :url "https://github.com/nervous-systems/cljs-lambda"
  :license {:name "Unlicense" :url "http://unlicense.org/UNLICENSE"}
  :scm {:name "git" :url "https://github.com/nervous-systems/cljs-lambda"}
  :dependencies [[org.clojure/clojure            "1.8.0"]
                 [org.clojure/clojurescript      "1.8.51"]
                 [org.clojure/core.async         "0.2.395"]
                 [org.clojure/tools.macro        "0.1.2"]
                 [funcool/promesa                "1.6.0"]
                 [io.nervous/cljs-nodejs-externs "0.2.0"]]
  :plugins [[lein-doo       "0.1.7"]
            [lein-npm       "0.6.2"]
            [lein-cljsbuild "1.1.4"]
            [lein-codox     "0.10.1"]]
  ;; Codox can't deal w/ deps.cljs, so isolate externs
  :source-paths ["src" "src-deps"]
  :cljsbuild
  {:builds [{:id "test"
             :source-paths ["src" "test"]
             :compiler {:output-to     "target/test/cljs-lambda.js"
                        :output-dir    "target/test"
                        :target        :nodejs
                        :optimizations :none
                        :main          cljs-lambda.test.runner}}]}
  :codox
  {:source-paths ["src"]
   :namespaces [cljs-lambda.util
                cljs-lambda.local
                cljs-lambda.macros
                cljs-lambda.context]
   :metadata {:doc/format :markdown}
   :language :clojurescript
   :html     {:transforms ~(read-string (slurp "codox-transforms.edn"))}
   :source-uri
   ~(str "https://github.com/nervous-systems/cljs-lambda/"
         "blob/master/cljs-lambda/{filepath}#L{line}")}
  :auto {"codox" {:file-pattern #"\.(clj[cs]?|md)$"
                  :paths ["doc" "src"]}})

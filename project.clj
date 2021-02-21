(defproject tablecloth.time "0.1.0-SNAPSHOT"
  :description "Time series manipulation library built on top of tablecloth"
  :url "http://example.com/FIXME"
  :license {:name "The MIT Licence"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.2"]
                 [scicloj/tablecloth "5.00-beta-29.1"]
                 [tick "0.4.27-alpha"]]
  :profiles {:dev {:dependencies [[scicloj/notespace "3-beta3"]
                                  [aerial.hanami "0.12.4"]]}})


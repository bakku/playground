(defproject two_sat "0.1.0-SNAPSHOT"
  :description "Solves the 2-SAT problem in linear time"
  :url "http://github.com/bakku/two_sat"
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot two-sat.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

(ns two-sat.core
  (:require [two-sat.sat-parser :as sat]
            [two-sat.graph :as graph])
  (:gen-class))

(defn- contradiction?
  [scc]
  (loop [remaining (seq scc)]
    (if (empty? remaining)
      false
      (let [curr (first remaining)
            inv (sat/inverse-literal curr)]
        (if (contains? scc inv)
          true
          (recur (rest remaining)))))))

(defn satisfiable?
  "Returns true of false if a 2 SAT expression is satisfiable
  or not. Look at the tests for examples."
  [expr]
  (let [sccs (-> (sat/parse expr)
                 (graph/find-sccs))]
    (not (some contradiction? sccs))))

(defn -main
  [& args]
  (if (satisfiable? (first args))
    (println "The expression is satisfiable")
    (println "The expression is NOT satisfiable")))

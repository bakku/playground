(ns two-sat.sat-parser-test
  (:require [clojure.test :refer :all]
            [two-sat.sat-parser :refer :all]))

(deftest parse-test
  (is (= (parse "(A OR nB) AND (nC OR D)")
         {"A" #{}
          "nA" #{"nB"}
          "B" #{"A"}
          "nB" #{}
          "C" #{"D"}
          "nC" #{}
          "D" #{}
          "nD" #{"nC"}}))
  (is (= (parse "(A OR nB) AND (nB OR C) AND (D OR nE)")
         {"A" #{}
          "nA" #{"nB"}
          "B" #{"A" "C"}
          "nB" #{}
          "C" #{}
          "nC" #{"nB"}
          "D" #{}
          "nD" #{"nE"}
          "E" #{"D"}
          "nE" #{}})))

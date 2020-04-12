(ns two-sat.core-test
  (:require [clojure.test :refer :all]
            [two-sat.core :refer :all]))

(deftest satisfiable-test
  (testing "satisfiable-expression"
    (is (satisfiable? "(A OR nB) AND (nB OR C) AND (D OR nE)"))
    (is (not (satisfiable? "(A OR B) AND (A OR nB) AND (nA OR B) AND (nA OR nB)")))))

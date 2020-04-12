(ns two-sat.graph-test
  (:require [clojure.test :refer :all]
            [two-sat.graph :refer :all]))

(def graph
  {"A" #{}
   "nA" #{"nB"}
   "B" #{"A"}
   "nB" #{}
   "C" #{"D"}
   "nC" #{}
   "D" #{}
   "nD" #{"nC"}})

(deftest depth-first-search-test
  (is (= (:result (depth-first-search graph ["A" "nA" "B" "nB" "C" "nC" "D" "nD"]))
         {"A" {:color :black
               :predecessor nil
               :discovered 1
               :finished 2}
          "nA" {:color :black
                :predecessor nil
                :discovered 3
                :finished 6}
          "B" {:color :black
               :predecessor nil
               :discovered 7
               :finished 8}
          "nB" {:color :black
                :predecessor "nA"
                :discovered 4
                :finished 5}
          "C" {:color :black
               :predecessor nil
               :discovered 9
               :finished 12}
          "nC" {:color :black
                :predecessor nil
                :discovered 13
                :finished 14}
          "D" {:color :black
               :predecessor "C"
               :discovered 10
               :finished 11}
          "nD" {:color :black
                :predecessor nil
                :discovered 15
                :finished 16}})))

(deftest transpose-test
  (is (= (transpose graph)
         {"A" #{"B"}
          "nA" #{}
          "B" #{}
          "nB" #{"nA"}
          "C" #{}
          "nC" #{"nD"}
          "D" #{"C"}
          "nD" #{}})))

(deftest find-sccs-test
  (let [result (find-sccs {"a" #{"b"}
                           "b" #{"c" "e" "f"}
                           "c" #{"d" "g"}
                           "d" #{"c" "h"}
                           "e" #{"a" "f"}
                           "f" #{"g"}
                           "g" #{"f" "h"}
                           "h" #{"h"}})]
    (is (= (count result) 4))
    (is (some #(= #{"a" "b" "e"} %) result))
    (is (some #(= #{"c" "d"} %) result))
    (is (some #(= #{"f" "g"} %) result))
    (is (some #(= #{"h"} %) result))))

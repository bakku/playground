;; Your goal in this kata is to implement a difference function, which subtracts one list from another and returns the result.
;; 
;; It should remove all values from list a, which are present in list b.
;; 
;; (= (array-diff [1 2] [1]) [2])
;; 
;; If a value is present in b, all of its occurrences must be removed from the other:
;; 
;; (= (array-diff [1,2,2,2,3] [2]) [1,3])

(ns codewars.array-diff
  (:require [clojure.test :refer [is]]))

(defn- coll-contains?
  [coll a]
  (some #(= a %) coll))

(defn array-diff
  {:test #(do
            (is (= (array-diff [1 2] [1]) [2]))
            (is (= (array-diff [1,2,2,2,3] [2]) [1,3])))}
  [a b]
  (filterv (partial (complement coll-contains?) b) a))

(test #'array-diff)

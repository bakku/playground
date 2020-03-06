;; Given two arrays a and b write a function (comp-same a b) that
;; checks whether the two arrays have the "same" elements, with the
;; same multiplicities. "Same" means, here, that the elements in b
;; are the elements in a squared, regardless of the order.

(ns codewars.comp-same-squared
  (:require [clojure.test :refer [is]]))

(defn comp-same
  {:test #(do
            (is (comp-same [121 144 19 161 19 144 19 11]
                           [121 14641 20736 361 25921 361 20736 361])))}
  [a b]
  (if (or (nil? a) (nil? b))
    false
    (= (sort (map #(* % %) a)) (sort b))))

(test #'comp-same)

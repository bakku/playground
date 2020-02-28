;; Write a function that takes an array of numbers (integers for the tests)
;; and a target number. It should find two different items in the array
;; that, when added together, give the target value. The indices of these
;; items should then be returned in a tuple like so: (index1, index2).
;;
;; For the purposes of this kata, some tests may have multiple answers;
;; any valid solutions will be accepted.
;;
;; The input will always be valid (numbers will be an array of length 2
;; or greater, and all of the items will be numbers; target will always
;; be the sum of two different items from that array).
;;
;; Based on: http://oj.leetcode.com/problems/two-sum/
;;
;; twoSum [1, 2, 3] 4 === (0, 2)

(ns codewars.two-sum
  (:require [clojure.test :refer [with-test is]]))

(defn twosum
  {:test #(do
            (is (= (sort < (twosum '[1 2 3] 4)) '[0 2]))
            (is (= (sort < (twosum '[1234 5678 9012] 14690)) '[1 2]))
            (is (= (sort < (twosum '[2 2 3] 4)) '[0 1])))}
  [numbers target]
  (let [possibilities (for [x (range (count numbers))
                            y (range (count numbers))
                            :when (and (not= x y) (< x y))]
                        [x y])]
    (first (filter #(= target (+ (numbers (% 0))
                                 (numbers (% 1))))
                   possibilities))))

(test #'twosum)

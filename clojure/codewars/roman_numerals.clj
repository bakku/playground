;; Create a function that takes a Roman numeral as its argument
;; and returns its value as a numeric decimal integer.
;; You don't need to validate the form of the Roman numeral.
;;
;; Modern Roman numerals are written by expressing each decimal
;; digit of the number to be encoded separately, starting with
;; the leftmost digit and skipping any 0s. So 1990 is rendered
;; "MCMXC" (1000 = M, 900 = CM, 90 = XC) and 2008 is rendered
;; "MMVIII" (2000 = MM, 8 = VIII). The Roman numeral for
;; 1666, "MDCLXVI", uses each letter in descending order.
;;
;; Example:
;;
;; (translate-roman-numerals "XXI") ;; should return 21
;;
;; Help:
;;
;; Symbol    Value
;; I          1
;; V          5
;; X          10
;; L          50
;; C          100
;; D          500
;; M          1,000

(ns codewars.roman-numerals
  (:require [clojure.test :refer [are]]))

(def value-map
  {\I 1
   \V 5
   \X 10
   \L 50
   \C 100
   \D 500
   \M 1000})

(defn assign-signs
  [nums]
  (loop [remaining nums
         result    []]
    (if (empty? remaining)
      result
      (let [fst (first remaining)
            snd (first (rest remaining))]
        (if (and snd (< fst snd))
          (recur (rest remaining)
                 (conj result (* fst -1)))
          (recur (rest remaining)
                 (conj result fst)))))))

(defn translate-roman-numerals
  {:test #(are [given-value calculated-value] (= given-value calculated-value)
            1     (translate-roman-numerals "I")
            4     (translate-roman-numerals "IV")
            2008  (translate-roman-numerals "MMVIII")
            1666  (translate-roman-numerals "MDCLXVI"))}
  [roman]
  (let [as-nums        (map value-map roman)
        nums-with-sign (assign-signs as-nums)]
    (apply + nums-with-sign)))

(test #'translate-roman-numerals)

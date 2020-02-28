;; This time no story, no theory. The examples below show you how to write function accum:
;;
;; Examples:
;;
;; accum("abcd") -> "A-Bb-Ccc-Dddd"
;; accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
;; accum("cwAt") -> "C-Ww-Aaa-Tttt"
;;
;; The parameter of accum is a string which includes only letters from a..z and A..Z.

(ns codewars.accum
  (:require [clojure.test :refer [is]]))

(defn char-to-lower
  [c]
  (java.lang.Character/toLowerCase c))

(defn char-to-upper
  [c]
  (java.lang.Character/toUpperCase c))

(defn accum
  {:test #(do
            (is (= (accum "ZpglnRxqenU") "Z-Pp-Ggg-Llll-Nnnnn-Rrrrrr-Xxxxxxx-Qqqqqqqq-Eeeeeeeee-Nnnnnnnnnn-Uuuuuuuuuuu"))
            (is (= (accum "NyffsGeyylB") "N-Yy-Fff-Ffff-Sssss-Gggggg-Eeeeeee-Yyyyyyyy-Yyyyyyyyy-Llllllllll-Bbbbbbbbbbb"))
            (is (= (accum "MjtkuBovqrU") "M-Jj-Ttt-Kkkk-Uuuuu-Bbbbbb-Ooooooo-Vvvvvvvv-Qqqqqqqqq-Rrrrrrrrrr-Uuuuuuuuuuu"))
            (is (= (accum "EvidjUnokmM") "E-Vv-Iii-Dddd-Jjjjj-Uuuuuu-Nnnnnnn-Oooooooo-Kkkkkkkkk-Mmmmmmmmmm-Mmmmmmmmmmm"))
            (is (= (accum "HbideVbxncC") "H-Bb-Iii-Dddd-Eeeee-Vvvvvv-Bbbbbbb-Xxxxxxxx-Nnnnnnnnn-Cccccccccc-Ccccccccccc")))}
  [s]
  (let [index-amounts  (map inc (range (count s)))
        first-to-upper #(assoc % 0 (char-to-upper (first %)))]
    (clojure.string/join "-"
                         (map #(apply str (first-to-upper (vec (repeat %1 (char-to-lower %2)))))
                              index-amounts
                              s))))

(test #'accum)

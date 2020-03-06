;; Complete the solution so that it splits the string into pairs of
;; two characters. If the string contains an odd number of characters
;; then it should replace the missing second character of the final
;; pair with an underscore ('_').
;;
;; Examples:
;;
;; (solution "abc")  ; => ["ab" "c_"]
;; (solution "abcd") ; => ["ab" "cd"]

(ns codewars.split-str
  (:require [clojure.test :refer [is]]))

(defn solution
  {:test #(do
            (is (= ["cd" "ab" "ef" "g_"] (solution "cdabefg")))
            (is (= ["cd" "ab" "ef" "gh"] (solution "cdabefgh")))
            (is (= ["ab" "cd"] (solution "abcd"))))}
  [s]
  (let [parts        (partition 2 s)
        parts-as-str (mapv #(apply str %) parts)]
    (if (odd? (count s))
      (conj parts-as-str (str (last s) "_"))
      parts-as-str)))

(test #'solution)

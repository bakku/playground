;; For building the encrypted string:
;; Take every 2nd char from the string, then the other chars,
;; that are not every 2nd char, and concat them as new String.
;; Do this n times!
;;
;; Examples:
;;
;; "This is a test!", 1 -> "hsi  etTi sats!"
;; "This is a test!", 2 -> "hsi  etTi sats!" -> "s eT ashi tist!"
;;
;; Write two methods:
;;
;; string Encrypt(string text, int n)
;; string Decrypt(string encryptedText, int n)
;;
;; For both methods:
;; If the input-string is null or empty return exactly this value!
;; If n is <= 0 then return the input text.

(ns codewars.alternating-split
  (:require [clojure.test :refer [is]]))

(defn encrypt
  {:test #(do
            (is (= "This is a test!" (encrypt "This is a test!" 0)))
            (is (= "hsi  etTi sats!" (encrypt "This is a test!" 1)))
            (is (= "s eT ashi tist!" (encrypt "This is a test!" 2)))
            (is (= " Tah itse sits!" (encrypt "This is a test!" 3)))
            (is (= "This is a test!" (encrypt "This is a test!" 4)))
            (is (= "This is a test!" (encrypt "This is a test!" -1)))
            (is (= "hskt svr neetn!Ti aai eyitrsig"
                   (encrypt "This kata is very interesting!" 1))))}
  [st n]
  (if (<= n 0)
    st
    (let [fst-indices (filter odd? (range (count st)))
          snd-indices (filter even? (range (count st)))
          fst-part    (apply str (map #(get st %) fst-indices))
          snd-part    (apply str (map #(get st %) snd-indices))]
      (recur (apply str fst-part snd-part)
             (dec n)))))

(test #'encrypt)

(defn decrypt
  {:test #(do
            (is (= "This is a test!" (decrypt "This is a test!" 0)))
            (is (= "This is a test!" (decrypt "hsi  etTi sats!" 1)))
            (is (= "This is a test!" (decrypt "s eT ashi tist!" 2)))
            (is (= "This is a test!" (decrypt " Tah itse sits!" 3)))
            (is (= "This is a test!" (decrypt "This is a test!" 4)))
            (is (= "This is a test!" (decrypt "This is a test!" -1)))
            (is (= "This kata is very interesting!"
                   (decrypt "hskt svr neetn!Ti aai eyitrsig" 1))))}
  [st n]
  (if (<= n 0)
    st
    (let [fst-part (take (Math/floor (/ (count st) 2)) st)
          snd-part (drop (Math/floor (/ (count st) 2)) st)
          result   (apply str (map #(str %1 %2) snd-part fst-part))]
      (if (= (mod (count st) 2) 0) ;; add last char of snd-part if necessary
        (decrypt result (dec n))
        (decrypt (str result (last st)) (dec n))))))

(test #'decrypt)

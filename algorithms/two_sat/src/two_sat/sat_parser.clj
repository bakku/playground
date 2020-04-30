(ns two-sat.sat-parser
  (:require [clojure.string :as string]))

(defn- split-to-literals
  [formula]
  (let [wo-brackets (string/replace formula #"\(|\)" "")
        clauses (string/split wo-brackets #" AND ")]
    (map #(string/split % #" OR ") clauses)))

(defn- neg-literal?
  [literal]
  (string/starts-with? literal "n"))

(defn- pos-literal?
  [literal]
  ((complement neg-literal?) literal))

(defn- get-pos-literal
  [literal]
  (if (string/starts-with? literal "n")
    (str (last literal))
    literal))

(defn- get-neg-literal
  [literal]
  (str "n" (get-pos-literal literal)))

(defn inverse-literal
  [literal]
  (if (pos-literal? literal)
    (get-neg-literal literal)
    (get-pos-literal literal)))

(defn- assoc-if-empty
  [m k v]
  (if (contains? m k)
    m
    (assoc m k v)))

(defn- insert-vertices
  [graph clause]
  (let [fst-pos (get-pos-literal (first clause))
        fst-neg (get-neg-literal fst-pos)
        snd-pos (get-pos-literal (second clause))
        snd-neg (get-neg-literal snd-pos)]
    (-> graph
        (assoc-if-empty fst-pos #{})
        (assoc-if-empty fst-neg #{})
        (assoc-if-empty snd-pos #{})
        (assoc-if-empty snd-neg #{}))))

(defn- insert-edges
  [graph clause]
  (let [fst (first clause)
        snd (second clause)
        inv-fst (inverse-literal fst)
        inv-snd (inverse-literal snd)
        fst-vertices (graph inv-fst)
        snd-vertices (graph inv-snd)]
    (-> graph
        (assoc inv-fst (conj fst-vertices snd))
        (assoc inv-snd (conj snd-vertices fst)))))

(defn- insert-clause
  [graph clause]
  (-> graph
      (insert-vertices clause)
      (insert-edges clause)))

(defn- build-graph
  [literals]
  (reduce insert-clause {} literals))

(defn parse
  "Parses a 2 SAT formula of the form (A OR nB) AND (B OR nC)
  and builds a graph in form of a map with the vertices as keys
  and a set of edges as values. For examples take a look at the tests."
  [formula]
  (-> (split-to-literals formula)
      build-graph))

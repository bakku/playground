(ns two-sat.graph)

(declare depth-first-search-visit)

(defn- initial-config
  [vertices]
  (apply merge (map #(hash-map % {:color :white
                                  :predecessor nil
                                  :discovered nil
                                  :finished nil})
                    vertices)))

(defn- alter-state-on-visit
  [{:keys [time] :as state} vertex]
  (-> state
      (assoc-in [:result vertex :color] :gray)
      (assoc-in [:result vertex :discovered] (inc time))
      (assoc :time (inc time))))

(defn- alter-state-after-visit
  [{:keys [time] :as state} vertex]
  (-> state
      (assoc-in [:result vertex :color] :black)
      (assoc-in [:result vertex :finished] (inc time))
      (assoc :time (inc time))))

(defn- set-predecessor
  [state vertex pred]
  (assoc-in state [:result vertex :predecessor] pred))

(defn- perform-visit
  [state vertex]
  (let [modified-state (alter-state-on-visit state vertex)
        state-after-children-visit (reduce (partial depth-first-search-visit vertex)
                                           modified-state
                                           (get-in state [:adjacency vertex]))]
    (alter-state-after-visit state-after-children-visit vertex)))

(defn- depth-first-search-visit
  ([state vertex]
   (if (= :white (get-in state [:result vertex :color]))
     (perform-visit state vertex)
     state))
  ([pred state vertex]
   (if (= :white (get-in state [:result vertex :color]))
     (perform-visit (set-predecessor state vertex pred) vertex)
     state)))

(defn depth-first-search
  "Executes a depth first search and returns a complete
  result map with all information the depth first search
  was able to acquire."
  [graph order]
  (let [initial-state {:adjacency graph
                       :result (initial-config (keys graph))
                       :time 0}]
    (reduce depth-first-search-visit
            initial-state
            order)))

(defn- build-empty-graph
  [graph]
  (apply merge (map #(hash-map % #{}) (keys graph))))

(defn- add-transposed-edges
  [graph [vertex edges]]
  (reduce #(assoc %1 %2 (conj (%1 %2) vertex))
          graph
          edges))

(defn transpose
  "In simple words, this function basically changes all edges
  to point into the other direction."
  [graph]
  (let [transposed (build-empty-graph graph)]
    (reduce add-transposed-edges
            transposed
            graph)))

(defn- vertices-in-decreasing-finish-time
  [dfs-result]
  (->> (sort-by #(:finished (second %))
               >
               dfs-result)
       (map first)))

(defn- find-roots
  [dfs-result]
  (->> dfs-result
       (filter #(nil? (:predecessor (second %))))
       (map first)))

(defn- expand-tree
  [dfs-result tree]
  (->> (filter #(and (contains? tree (:predecessor (second %)))
                     (not (contains? tree %)))
               dfs-result)
       (map first)
       (apply conj tree)))

(defn- get-depth-first-tree
  [dfs-result root]
  (loop [tree #{root}]
    (let [new-tree (expand-tree dfs-result tree)]
      (if (= tree new-tree)
        tree
        (recur new-tree)))))

(defn- extract-sccs
  [dfs-result]
  (let [roots (find-roots dfs-result)]
    (mapv (partial get-depth-first-tree dfs-result) roots)))

(defn find-sccs
  "Implements the algorithm of the CLRS book to find all
  strongly connected components."
  [graph]
  (let [first-dfs (depth-first-search graph (keys graph))
        transposed (transpose graph)
        order (vertices-in-decreasing-finish-time (:result first-dfs))
        second-dfs (depth-first-search transposed order)]
    (extract-sccs (:result second-dfs))))

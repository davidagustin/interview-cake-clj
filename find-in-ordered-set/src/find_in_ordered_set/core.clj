(ns find-in-ordered-set.core
  (:gen-class))

(def ^:private gt? (comp pos? compare))
(def ^:private lt? (comp neg? compare))
(def ^:private eq? (comp zero? compare))

(defn- midpoint [start end]
  (-> (- end start)
      (/ 2)
      int
      (+ start)))

(defn binary-search
  "O(lgn) - returns index of item in sorted vector, -1 if not found."
  [arr item]
  (loop [start 0
         end (count arr)]
    (let [mid (midpoint start end)
          length (- end start)
          mid-item (get arr mid)
          first-item (get arr start)]
      (cond
        (<= length 1) (if (eq? first-item item) start -1)
        (eq? mid-item item) mid
        (gt? mid-item item) (recur start mid)
        :else (recur mid end)))))

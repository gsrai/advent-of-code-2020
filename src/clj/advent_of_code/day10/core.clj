(ns advent-of-code.day10.core
  (:require [clojure.string :refer [split-lines]]))

;; https://adventofcode.com/2020/day/10

(defn solve [diff-map]
  (* (get diff-map 1) (get diff-map 3)))

(defn calc-diffs [coll]
  (loop [[x & xs] (conj coll 0)
         diffs {1 0 2 0 3 0}]
    (if (nil? xs)
      (update diffs 3 inc)
      (recur xs (update diffs (- (first xs) x) inc)))))

(defn part1 [data]
  (->> data
       split-lines
       (map #(Integer/parseInt %))
       sort
       calc-diffs
       solve))

(defn solve-p2 [data]
  (let [fn (fn [self pos skipped data]
             (if (>= pos (count data))
               1
               (let [prev (data (dec pos))
                     curr (data pos)
                     diff (- curr prev)
                     newSkipped (+ skipped diff)]
                 (cond
                   (< newSkipped 3) (+
                                     (self self (inc pos) 0 data)
                                     (self self (inc pos) newSkipped data))
                   (= newSkipped 3) (self self (inc pos) 0 data)
                   (> newSkipped 3) 0))))
        memfn (memoize fn)]
    (memfn memfn 1 0 data)))



(defn part2 [data]
  (->> data
       split-lines
       (map #(Integer/parseInt %))
       (#(conj % 0 (+ (apply  max %) 3)))
       sort
       vec
       solve-p2))

(def data (->> "resources/day10/data.txt" slurp))

(defn main []
  (println "part1 solution" (part1 data))
  (println "part2 solution" (part2 data)))

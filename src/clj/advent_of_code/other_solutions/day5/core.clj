(ns advent-of-code.other-solutions.day5.core
  (:require [clojure.set :refer [difference]]
            [clojure.string :refer [split-lines join]]))

(def rules {\B \1 \F \0 \R \1 \L \0})

(defn process-line [line]
  (->> line
       (re-matches #"([FB]{7})([RL]{3})")
       first
       (map rules)
       (join)))

(def seat-ids (->> "resources/day5/data.txt"
                   slurp
                   split-lines
                   (map process-line)
                   (map #(Integer/parseInt % 2))
                   (into #{})))

(defn part1 []
  (apply max seat-ids))

(defn part2 []
  (let [high (inc (apply max seat-ids))
        low (apply min seat-ids)]
    (difference (into #{} (range low high)) seat-ids)))

(defn main []
  (println "part1 solution" (part1))
  (println "part2 solution" (part2)))
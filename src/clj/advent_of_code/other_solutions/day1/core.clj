(ns advent-of-code.other-solutions.day1.core
  (:require [clojure.string :as s]
            [advent-of-code.other-solutions.day1.part1 :as part1]
            [advent-of-code.other-solutions.day1.part2 :as part2]))

;; https://www.youtube.com/watch?v=Vp8RbO7l6eg

(defn read-numbers [filename]
  (->> filename
       slurp
       s/split-lines
       (map #(Integer/parseInt %))
       set))

(def numbers (->> "resources/day1/data.txt" read-numbers))

(defn main []
  (println "part1 solution" (part1/find-solution numbers))
  (println "part2 solution" (part2/find-solution numbers)))
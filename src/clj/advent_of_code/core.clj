(ns advent-of-code.core
  (:require [advent-of-code.day1.core :as day1]
            [advent-of-code.day2.core :as day2]
            [advent-of-code.day3.core :as day3]
            [advent-of-code.day4.core :as day4]
            [advent-of-code.other-solutions.day1.core :as alt-day1]
            [advent-of-code.other-solutions.day2.core :as alt-day2]))

(defn run-solutions []
  (println "\nDay 1:")
  (day1/main)
  (println "\nDay 2:")
  (day2/main)
  (println "\nDay 3:")
  (day3/main)
  (println "\nDay 4:")
  (day4/main))

(defn run-alt-solutions []
  (println "\nAlternate Solution for Day 1:")
  (alt-day1/main)
  (println "\nAlternate Solution for Day 2:")
  (alt-day2/main))

(defn -main []
  (run-solutions)
  (println (run-alt-solutions)))
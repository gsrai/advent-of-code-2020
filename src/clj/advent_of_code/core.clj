(ns advent-of-code.core
  (:require [advent-of-code.day1.core :as day1]
            [advent-of-code.day2.core :as day2]
            [advent-of-code.day3.core :as day3]
            [advent-of-code.day4.core :as day4]
            [advent-of-code.day5.core :as day5]
            [advent-of-code.day6.core :as day6]
            [advent-of-code.day7.core :as day7]
            [advent-of-code.day8.core :as day8]
            [advent-of-code.day9.core :as day9]
            [advent-of-code.day10.core :as day10]
            [advent-of-code.other-solutions.day1.core :as alt-day1]
            [advent-of-code.other-solutions.day2.core :as alt-day2]
            [advent-of-code.other-solutions.day5.core :as alt-day5]))

(defn run-solutions []
  (println "\nDay 1:")
  (day1/main)
  (println "\nDay 2:")
  (day2/main)
  (println "\nDay 3:")
  (day3/main)
  (println "\nDay 4:")
  (day4/main)
  (println "\nDay 5:")
  (day5/main)
  (println "\nDay 6:")
  (day6/main)
  (println "\nDay 7:")
  (day7/main)
  (println "\nDay 8:")
  (day8/main)
  (println "\nDay 9:")
  (day9/main)
  (println "\nDay 10:")
  (day10/main))

(defn run-alt-solutions []
  (println "\nAlternate Solution for Day 1:")
  (alt-day1/main)
  (println "\nAlternate Solution for Day 2:")
  (alt-day2/main)
  (println "\nAlternate Solution for Day 5:")
  (alt-day5/main))

(defn -main []
  (run-solutions)
  (println (run-alt-solutions)))
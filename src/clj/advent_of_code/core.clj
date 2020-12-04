(ns advent-of-code.core
  (:require [advent-of-code.day1.core :as day1]
            [advent-of-code.day2.core :as day2]
            [advent-of-code.day3.core :as day3]
            [advent-of-code.day4.core :as day4]))

(defn -main []
  (println "\nDay 1:")
  (day1/main)
  (println "\nDay 2:")
  (day2/main)
  (println "\nDay 3:")
  (day3/main)
  (println "\nDay 4:")
  (day4/main))
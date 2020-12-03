(ns advent-of-code.core
  (:require [advent-of-code.day1.core :as day1]
            [advent-of-code.day2.core :as day2]
            [advent-of-code.day3.core :as day3]))

(defn -main []
  (day1/main)
  (day2/main)
  (day3/main))
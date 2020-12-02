(ns advent-of-code.core
  (:require [advent-of-code.day1.core :as day1]
            [advent-of-code.day2.core :as day2]))

(defn -main []
  (day1/main)
  (day2/main))
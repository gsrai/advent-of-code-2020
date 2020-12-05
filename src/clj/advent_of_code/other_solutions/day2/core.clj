(ns advent-of-code.other-solutions.day2.core
  (:require [clojure.java.io :as io]
            [advent-of-code.other-solutions.day2.part1 :as part1]
            [advent-of-code.other-solutions.day2.part2 :as part2]))

;; https://www.youtube.com/watch?v=m8lX3B6R8qY

(defn parse-line [line]
  (let [[_ min max ch password] (re-matches #"(\d+)-(\d+) (\w): (\w+)" line)]
    {:min (Integer/parseInt min)
     :max (Integer/parseInt max)
     :ch (nth ch 0)
     :password password}))

(defn read-database [filename]
  (with-open [rdr (io/reader filename)]
    (->> rdr
         line-seq
         (mapv parse-line))))

(def passwords (->> "resources/day2/data.txt" read-database))

(defn main []
  (println "part1 solution" (part1/validate-passwords passwords))
  (println "part2 solution" (part2/validate-passwords passwords)))
(ns advent-of-code.day6.core
  (:require [clojure.test :refer [deftest is run-tests]]
            [clojure.set :refer [intersection]]
            [clojure.string :refer [split-lines blank? join]]))

;; https://adventofcode.com/2020/day/6

(defn part1 [data]
  (->> data
       split-lines
       (partition-by blank?)
       (filter #(not (blank? (first %))))
       (map #(join %))
       (map #(into #{} %))
       (map count)
       (reduce +)))

(defn find-common [[x & xs]]
  (reduce
   (fn [acc cv]
     (intersection acc (into #{} cv))) (into #{} x) xs))

(defn part2 [data]
  (->> data
       clojure.string/split-lines
       (partition-by clojure.string/blank?)
       (filter #(not (clojure.string/blank? (first %))))
       (map find-common)
       (map count)
       (reduce +)))

(def test-data "abc

a
b
c

ab
ac

a
a
a
a

b")

(def data (->> "resources/day6/data.txt" slurp))

;; part 1
(deftest test-part1
  (is (= (part1 test-data) 11)))

;; part 2
(deftest test-part2
  (is (= (part2 test-data) 6)))

(defn main []
  (println "part1 solution" (part1 data))
  (println "part2 solution" (part2 data))
  (run-tests 'advent-of-code.day6.core))

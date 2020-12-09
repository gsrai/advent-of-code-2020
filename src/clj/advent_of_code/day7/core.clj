(ns advent-of-code.day7.core
  (:require [clojure.test :refer [deftest is run-tests]]
            [clojure.string :refer [split-lines]]))

;; https://adventofcode.com/2020/day/7

(defn parse-bag [[[_ __ container] & elements]]
  (->> elements
       (map (fn [[_ n bag]] [(Integer/parseInt n) bag]))
       (assoc {} container)))

(defn sub-contains? [container own-bag database]
  (->> container
       database
       (some (fn [[n bag]]
               (or
                (= bag own-bag)
                (sub-contains? bag own-bag database))))))

(defn find-in [bag database]
  (filter (fn [[container _]]
            (sub-contains? container bag database)) database))

(defn sub-count [container database]
  (->> container
       database
       (reduce (fn [acc [n bag]]
                 (+ acc n (* n (sub-count bag database)))) 0)))

(defn part1 [data]
  (->> data
       split-lines
       (map #(re-seq #"(?:^|(\d+) )(\w+ \w+) bags?" %))
       (map parse-bag)
       (apply merge)
       (find-in "shiny gold")
       count))

(defn part2 [data]
  (->> data
       split-lines
       (map #(re-seq #"(?:^|(\d+) )(\w+ \w+) bags?" %))
       (map parse-bag)
       (apply merge)
       (sub-count "shiny gold")))

(def test-data-part-1 "light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.")

(def test-data-part-2 "
shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.
")

(def data (->> "resources/day7/data.txt" slurp))

;; part 1
(deftest test-part-1
  (is (= (part1 test-data-part-1) 4)))

;; part 2
(deftest test-part-2
  (is (= (part2 test-data-part-2) 126)))

(defn main []
  (println "part1 solution" (part1 data))
  (println "part2 solution" (part2 data))
  (run-tests 'advent-of-code.day7.core))

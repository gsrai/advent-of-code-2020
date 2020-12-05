(ns advent-of-code.day5.core
  (:require [clojure.test :refer [deftest is run-tests]]
            [clojure.set :refer [difference]]
            [clojure.string :refer [split-lines]]))

;; https://adventofcode.com/2020/day/5

;; FBFBBFF      RLR
;; row part     seat/column part  
;; 1 of 128     1 of 8
;; (0-127)      (0-7)
;; 2^7          2^3
;; 
;; FBFBBFFRLR binary space partitioning
;; 
;; SeatID:  row * 7 + column

(defn find-row [coll]
  (loop [[x & xs] coll
         upper 127
         lower 0]
    (if (some? x)
      (cond (= x \F) (recur xs (dec (int (Math/ceil (/ (+ upper lower) 2)))) lower)
            (= x \B) (recur xs upper (int (Math/ceil (/ (+ upper lower) 2)))))
      upper)))

(defn find-column [coll]
  (loop [[x & xs] coll
         upper 7
         lower 0]
    (if (some? x)
      (cond (= x \L) (recur xs (dec (int (Math/ceil (/ (+ upper lower) 2)))) lower)
            (= x \R) (recur xs upper (int (Math/ceil (/ (+ upper lower) 2)))))
      upper)))

(defn process-line [line]
  (let [[_ row-data col-data] (re-matches #"([FB]{7})([RL]{3})" line)
        row (find-row row-data)
        col (find-column col-data)]
    (+ (* row 8) col)))

(defn part1 [data]
  (->> data
       split-lines
       (map process-line)
       (apply max)))

(defn part2 [data]
  (let [result (->> data
                    split-lines
                    (map process-line))
        high (apply max result)
        low (apply min result)]
    (difference (into #{} (range low (inc high))) (into #{} result))))

(def test-data "FBFBBFFRLR
BFFFBBFRRR
FFFBBBFRRR
BBFFBBFRLL")

(def data (->> "resources/day5/data.txt" slurp))

;; part 1
(deftest sanity-check
  (is (= (part1 test-data) 820)))

(defn main []
  (println "part1 solution" (part1 data))
  (println "part2 solution" (part2 data))
  (run-tests 'advent-of-code.day5.core))

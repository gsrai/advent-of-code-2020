(ns advent-of-code.day9.core
  (:require [clojure.string :refer [split-lines]]))

;; https://adventofcode.com/2020/day/9

(defn invalid? [preamble n]
  (empty? (filter #(= n %) (for [x preamble y preamble] (+ x y)))))

(defn validate [coll]
  (let [preamble (->> coll (take 25))
        n (->> coll (drop 25) first)]
    (when (some? n)
      (if (invalid? preamble n)
        n
        (validate (drop 1 coll))))))

(defn part1 [data]
  (->> data
       split-lines
       (map #(Long/parseLong %))
       (validate)))

(defn find-contiguous [target coll]
  (loop [acc []
         [x & xs :as all] coll]
    (let [arr (conj acc x)
          a (apply + acc)]
      (when (some? x)
        (cond (< a target) (recur arr xs)
              (> a target) (recur (subvec acc 1) all)
              (= a target) (+ (apply max acc) (apply min acc)))))))

(defn part2 [data]
  (->> data
       split-lines
       (mapv #(Long/parseLong %))
       (find-contiguous 1504371145)))

(def data (->> "resources/day9/data.txt" slurp))

(defn main []
  (println "part1 solution" (part1 data))
  (println "part2 solution" (part2 data)))

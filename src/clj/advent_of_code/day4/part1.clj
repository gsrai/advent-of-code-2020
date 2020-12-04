(ns advent-of-code.day4.part1
  (:require [clojure.set :refer [difference]]
            [clojure.string :refer [join split blank?]]))

(def required-fields [:byr :iyr :eyr :hgt :hcl :ecl :pid])

(def optional-fields [:cid])

(defn passport-valid? [passport]
  (empty? (difference (into #{} (keys (apply dissoc passport optional-fields))) required-fields)))

(defn validate-passports [passports]
  (filter passport-valid? passports))

(defn parse-data [data]
  (->> (split data #"\n")
       (partition-by blank?)
       (map #(apply (fn [& coll] (join " " coll)) %))
       (filter seq)))

(defn str-kv-to-map [s]
  (->> (split s #" ")
       (map #(split % #":"))
       (reduce (fn [acc [k v]] (merge acc {(keyword k) v})) {})))

(defn run [data]
  (->> data
       parse-data
       (map str-kv-to-map)
       validate-passports
       count))
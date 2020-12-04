(ns advent-of-code.day4.part2
  (:require [clojure.set :refer [difference]]
            [clojure.string :refer [join split blank?]]))

(def required-fields [:byr :iyr :eyr :hgt :hcl :ecl :pid])

(def optional-fields [:cid])

(defn str-within-ints? [s x y]
  (let [n (Integer/parseInt s)]
    (and (>= n x) (<= n y))))

(defn validate-byr [passport]
  (str-within-ints? (:byr passport) 1920 2002))

(defn validate-iyr [passport]
  (str-within-ints? (:iyr passport) 2010 2020))

(defn validate-eyr [passport]
  (str-within-ints? (:eyr passport) 2020 2030))

(defn validate-hgt [passport]
  (let [h (re-matches #"([0-9]{2,3})(cm|in)" (:hgt passport))]
    (if (some? h)
      (if (= (last h) "cm")
        (str-within-ints? (second h) 150 193)
        (str-within-ints? (second h) 59 76))
      false)))

(defn validate-hcl [passport]
  (some? (re-matches #"#[0-9a-fA-F]{6}" (:hcl passport))))

(defn validate-ecl [passport]
  (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} (:ecl passport)))

(defn validate-pid [passport]
  (= 9 (count (:pid passport))))

(defn passport-valid? [passport]
  (empty? (difference (into #{} (keys (apply dissoc passport optional-fields))) required-fields)))

(defn fields-valid? [passport]
  (reduce #(and %1 %2) ((juxt validate-byr
                              validate-iyr
                              validate-eyr
                              validate-hgt
                              validate-hcl
                              validate-ecl
                              validate-pid) passport)))

(defn validate-passports [passports]
  (->> passports
       (filter passport-valid?)
       (filter fields-valid?)))

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

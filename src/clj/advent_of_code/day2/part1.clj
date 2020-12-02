(ns advent-of-code.day2.part1)

(defn within? [mini maxi n]
  (and (<= n maxi) (>= n mini)))

(defn password-valid? [pwd]
  (let [[mini maxi c s] pwd]
    (->> s
         (filter #(= % c))
         count
         (within? mini maxi))))

(defn validate-passwords [pwds]
  (count (filter password-valid? pwds)))
(ns advent-of-code.other-solutions.day1.part1)

(defn find-solution [numbers]
  (first (for [a numbers
               :let [b (- 2020 a)]
               :when (< a b)
               :when (contains? numbers b)] ;; contains on set is constant time
           (* a b))))
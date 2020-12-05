(ns advent-of-code.other-solutions.day1.part2)

(defn find-solution [numbers]
  (first (for [a numbers
               b numbers
               :let [c (- 2020 a b)]
               :when (< a b c)
               :when (contains? numbers c)]
           (* a b c))))
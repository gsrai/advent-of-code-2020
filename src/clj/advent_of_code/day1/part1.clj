(ns advent-of-code.day1.part1)

(defn foo [[x & xs] n]
  (let [ys (filter #(= n (+ x %)) xs)]
    (if (seq ys)
      (* x (first ys))
      (recur xs n))))
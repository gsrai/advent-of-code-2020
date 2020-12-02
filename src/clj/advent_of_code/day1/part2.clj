(ns advent-of-code.day1.part2)

(defn bar [[y & ys] x n]
  (if (some? y)
    (let [[z] (filter #(= n (+ x y %)) ys)]
      (if (some? z)
        (* x y z)
        (recur ys x n)))))

(defn foo [[x & xs] n]
  (let [result (bar xs x n)]
    (if (some? result)
      result
      (recur xs n))))
(ns advent-of-code.day3.part2)

(defn tree? [x]
  (= x \#))

(defn traverse-grid [grid ridx didx starting-pos tree-count]
  (let [next (nth grid didx nil)
        right-pos (+ starting-pos ridx)]
    (if (nil? next)
      tree-count
      (recur (drop didx grid) ridx didx right-pos
             (if (tree? (get next (mod right-pos (count next))))
               (inc tree-count)
               tree-count)))))

(def moves [[1 1]
            [3 1]
            [5 1]
            [7 1]
            [1 2]])

(defn foo [grid]
  (reduce * (map (fn [[right down]]
                   (traverse-grid grid right down 0 0)) moves)))
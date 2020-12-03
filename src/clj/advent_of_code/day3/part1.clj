(ns advent-of-code.day3.part1)

(defn tree? [x]
  (= x \#))

(defn traverse-grid [[_ & rest] starting-pos tree-count]
  (let [next (first rest)
        right-pos (+ starting-pos 3)]
    (if (nil? next)
      tree-count
      (recur rest right-pos
             (if (tree? (get next (mod right-pos (count next))))
               (inc tree-count)
               tree-count)))))
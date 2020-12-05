(ns advent-of-code.other-solutions.day2.part2)

(defn valid-password? [{:keys [min max ch password]}]
  (not=
   (= ch (nth password (dec min)))
   (= ch (nth password (dec max)))))

(defn validate-passwords [passwords]
  (->> passwords
       (filter valid-password?)
       count))
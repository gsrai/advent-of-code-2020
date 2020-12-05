(ns advent-of-code.other-solutions.day2.part1)

(defn valid-password? [{:keys [min max ch password]}]
  (let [times (->> password
                   (filter #(= ch %))
                   count)]
    (<= min times max)))

(defn validate-passwords [passwords]
  (->> passwords
       (filter valid-password?)
       count))
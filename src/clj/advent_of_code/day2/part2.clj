(ns advent-of-code.day2.part2)

(defn xor [a b]
  (and (not (and a b)) (or a b)))

(defn password-valid? [pwd]
  (let [[apos bpos c s] pwd
        v (->> s vec)
        a (v (dec apos))
        b (v (dec bpos))]
    (xor (= a c) (= b c))))

(defn validate-passwords [pwds]
  (count (filter password-valid? pwds)))
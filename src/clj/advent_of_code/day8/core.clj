(ns advent-of-code.day8.core
  (:require [clojure.test :refer [deftest is run-tests]]
            [clojure.string :refer [split-lines]]))

;; https://adventofcode.com/2020/day/8

(defn parse-instruction [index instruction]
  (let [[_ i n] (re-matches #"(\w+) ([-|\+]\d+)" instruction)]
    [(keyword (str index)) [i (Integer/parseInt n)]]))

(defn exec-instructions [instructions]
  (loop [program-count 0
         acc 0
         executed-instructions #{}]
    (let [[instruction-count [operator operand]] (get instructions program-count)]
      (when (not= program-count (count instructions))
        (if (contains? executed-instructions instruction-count)
          acc
          (cond (= operator "nop") (recur (inc program-count) acc (conj executed-instructions instruction-count))
                (= operator "jmp") (recur (+ program-count operand) acc (conj executed-instructions instruction-count))
                (= operator "acc") (recur (inc program-count) (+ acc operand) (conj executed-instructions instruction-count))))))))

(defn exec-program [instructions]
  (loop [program-count 0
         acc 0
         executed-instructions #{}]
    (let [[instruction-count [operator operand]] (get instructions program-count)]
      (if (= program-count (count instructions))
        acc
        (cond (= operator "nop") (recur (inc program-count) acc (conj executed-instructions instruction-count))
              (= operator "jmp") (recur (+ program-count operand) acc (conj executed-instructions instruction-count))
              (= operator "acc") (recur (inc program-count) (+ acc operand) (conj executed-instructions instruction-count)))))))

(defn terminates? [instructions]
  (loop [program-count 0
         executed-instructions #{}]
    (let [[ic [operator operand]] (get instructions program-count)]
      (if (not= program-count (count instructions))
        (if (contains? executed-instructions ic)
          false
          (cond (= operator "nop") (recur (inc program-count) (conj executed-instructions ic))
                (= operator "jmp") (recur (+ program-count operand) (conj executed-instructions ic))
                (= operator "acc") (recur (inc program-count) (conj executed-instructions ic))))
        true))))

(defn flip-instruction [[ic [operator operand]]]
  (cond (= operator "nop") [ic ["jmp" operand]]
        (= operator "jmp") [ic ["nop" operand]]
        :else [ic [operator operand]]))

(defn fix-corruption [corrupted-program]
  (loop [program-count 0]
    (if (= program-count (count corrupted-program))
      corrupted-program
      (let [modified-program (assoc corrupted-program program-count (flip-instruction (get corrupted-program program-count)))]
        (if (terminates? modified-program)
          modified-program
          (recur (inc program-count)))))))

(defn part1 [data]
  (->> data
       split-lines
       (map-indexed parse-instruction)
       (into [])
       exec-instructions))

(defn part2 [data]
  (->> data
       split-lines
       (map-indexed parse-instruction)
       (into [])
       fix-corruption
       exec-program))

(def test-data "nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
")

(def data (->> "resources/day8/data.txt" slurp))

;; part 1
(deftest test-part-1
  (is (= (part1 test-data) 5)))

;; part 2
(deftest test-part-2
  (is (= (part2 test-data) 8)))

(defn main []
  (println "part1 solution" (part1 data))
  (println "part2 solution" (part2 data))
  (run-tests 'advent-of-code.day8.core))

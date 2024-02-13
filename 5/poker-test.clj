(ns user
  (:require [clojure.test :refer :all]
            [user :refer :all]))

;; Helpers

(def nothing-hand ["2H" "5C" "8C" "TD" "QS"])
(def high-seven ["2H" "3S" "4C" "5C" "7D"])
(def pair-hand ["2H" "2S" "4C" "5C" "7D"])
(def two-pairs-hand ["2H" "2S" "4C" "4D" "7D"])
(def three-of-a-kind-hand ["2H" "2S" "2C" "4D" "7D"])
(def four-of-a-kind-hand ["2H" "2S" "2C" "2D" "7D"])
(def straight-hand ["2H" "3S" "6C" "5D" "4D"])
(def low-ace-straight-hand ["2H" "3S" "4C" "5D" "AD"])
(def high-ace-straight-hand ["TH" "AS" "QC" "KD" "JD"])
(def flush-hand ["2H" "4H" "5H" "9H" "7H"])
(def full-house-hand ["2H" "5D" "2D" "2C" "5S"])
(def straight-flush-hand ["2H" "3H" "6H" "5H" "4H"])
(def low-ace-straight-flush-hand ["2D" "3D" "4D" "5D" "AD"])
(def high-ace-straight-flush-hand ["TS" "AS" "QS" "KS" "JS"])

(def card-1 "TS")
(def card-2 "5H")
(def card-3 "AS")
(def card-4 "QD")
(def card-5 "2C")

;; Tests

(deftest suit-test
  (is (= (suit card-1) "S"))
  (is (= (suit card-2) "H"))
  (is (= (suit card-3) "S"))
  (is (= (suit card-4) "D"))
  (is (= (suit card-5) "C")))

(deftest rank-test
  (is (= (rank card-1) 10))
  (is (= (rank card-2) 5))
  (is (= (rank card-3) 14))
  (is (= (rank card-4) 12))
  (is (= (rank card-5) 2)))

(deftest pair?-test
  (is (= (pair? pair-hand) true))
  (is (= (pair? nothing-hand) false)))

(deftest three-of-a-kind?-test
  (is (= (three-of-a-kind? three-of-a-kind-hand) true))
  (is (= (three-of-a-kind? nothing-hand) false)))

(deftest four-of-a-kind?-test
  (is (= (four-of-a-kind? four-of-a-kind-hand) true))
  (is (= (four-of-a-kind? nothing-hand) false)))

(deftest flush?-test
  (is (= (flush? flush-hand) true))
  (is (= (flush? nothing-hand) false)))

(deftest full-house?-test
  (is (= (full-house? full-house-hand) true))
  (is (= (full-house? nothing-hand) false)))

(deftest two-pairs?-test
  (is (= (two-pairs? two-pairs-hand) true))
  (is (= (two-pairs? nothing-hand) false)))

(deftest straight?-test
  (is (= (straight? straight-hand) true))
  (is (= (straight? nothing-hand) false)))

(deftest straight-flush?-test
  (is (= (straight-flush? straight-flush-hand) true))
  (is (= (straight-flush? nothing-hand) false)))

(flush? straight-flush-hand)

(deftest value-test
  (is (= (value pair-hand) 1))
  (is (= (value nothing-hand) 0))
  (is (= (value four-of-a-kind-hand) 7))
  (is (= (value full-house-hand) 6))
  (is (= (value flush-hand) 5)))

(deftest kickers-test
  (is (= (kickers four-of-a-kind-hand) '(2 7)))
  (is (= (kickers pair-hand) '(2 7 5 4)))
  (is (= (kickers flush-hand) '(9 7 5 4 2)))
  (is (= (kickers nothing-hand) '(12 10 8 5 2)))
  (is (= (kickers full-house-hand) '(2 5))))

(deftest higher-kicker?-test
  (is (= (higher-kicker? (kickers flush-hand) (kickers pair-hand)) true))
  (is (= (higher-kicker? (kickers pair-hand) (kickers flush-hand)) false)))

(deftest beats?-test
  (is (= (beats? flush-hand pair-hand) true))
  (is (= (beats? pair-hand flush-hand) nil)))

(deftest winning-hand-test
  (is (= (winning-hand full-house-hand nothing-hand) ["2H" "5D" "2D" "2C" "5S"]))
  (is (= (winning-hand four-of-a-kind-hand flush-hand) ["2H" "2S" "2C" "2D" "7D"]))
  (is (= (winning-hand flush-hand full-house-hand) ["2H" "5D" "2D" "2C" "5S"]))
  (is (= (winning-hand pair-hand four-of-a-kind-hand) ["2H" "2S" "2C" "2D" "7D"]))
  (is (= (winning-hand full-house-hand pair-hand full-house-hand) '(["2H" "5D" "2D" "2C" "5S"] ["2H" "5D" "2D" "2C" "5S"]))))

(run-tests)

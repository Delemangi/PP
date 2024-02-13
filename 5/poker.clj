(ns user
  (:require [clojure.test :refer :all]))

;; 1

(defn suit "Get the suit of a card" [card]
  (str (second card)))

;; 2

(defn rank "Get the rank of a card" [card]
  (if (Character/isDigit (first card))
    (Integer/valueOf (str (first card)))
    (first (map {\T 10, \J 11, \Q 12, \K 13, \A 14} [(first card)]))))

;; 3

(defn rank-frequencies "Get the frequencies of the ranks of cards" [hand]
  (frequencies
   (map rank hand)))

(defn rank-frequencies-sorted "Get the sorted frequencies of the ranks of cards" [hand]
  (sort (vals (rank-frequencies hand))))

(defn pair? "Is the hand a pair?" [hand]
  (= '(1 1 1 2) (rank-frequencies-sorted hand)))

;; 4

(defn three-of-a-kind? "Is the hand three of a kind?" [hand]
  (= '(1 1 3) (rank-frequencies-sorted hand)))

;; 5

(defn four-of-a-kind? "Is the hand four of a kind?" [hand]
  (= '(1 4) (rank-frequencies-sorted hand)))

;; 6

(defn increasing-rank? "Check if the ranks are consecutive and increasing" [ranks]
  (if (< (count ranks) 2)
    true
    (and
     (= 1 (- (second ranks) (first ranks)))
     (increasing-rank? (rest ranks)))))

(defn flush? "Is the hand a flush?" [hand]
  (and
   (every?
    #(= (suit (first hand)) %)
    (map suit hand))
   (not (increasing-rank? (sort (map rank hand))))))

;; 7

(defn full-house? "Is the hand a full house?" [hand]
  (= '(2 3) (rank-frequencies-sorted hand)))

;; 8

(defn two-pairs? "Is the hand two pairs?" [hand]
  (= '(1 2 2) (rank-frequencies-sorted hand)))

;; 9

(defn straight? "Is the hand a straight?" [hand]
  (or
   (increasing-rank? (sort (map rank hand)))
   (increasing-rank? (sort (replace {14 1} (map rank hand))))))

;; 10

(defn straight-flush? "Is the hand a straight flush?" [hand]
  (and
   (straight? hand)
   (every?
    #(= (suit (first hand)) %)
    (map suit hand))))

;; 11

(defn value "Get the value of a hand" [hand]
  (cond
    (pair? hand) 1
    (two-pairs? hand) 2
    (three-of-a-kind? hand) 3
    (straight? hand) 4
    (flush? hand) 5
    (full-house? hand) 6
    (four-of-a-kind? hand) 7
    (straight-flush? hand) 8
    :else 0))

;; 12

(defn sort-ranks-by-frequency "Get ranks sorted by frequency, or sorted by ranks in case of ties" [hand]
  (map first (sort-by #(+ (* 10 (val %)) (key %)) > (rank-frequencies hand))))

(defn kickers-sort "Sort ranks in descending order if the value is 4 (straight), 5 (flush) or 8 (straight flush), otherwise sort by frequency, then rank" [hand value]
  (cond
    (contains? #{4 5 8} value) (sort > (map rank hand))
    (contains? #{2 6 7 3 1} value) (sort-ranks-by-frequency hand)
    :else (sort > (map rank hand))))

(defn kickers "Sort the hand by its kicker rule" [hand]
  (kickers-sort hand (value hand)))

;; 13

(defn higher-kicker? "Find the first pair of elements that are not equal, and check if the first kicker is higher" [kicker1 kicker2]
  (cond
    (= (count kicker1) 0) false
    (= (first kicker1) (first kicker2)) (higher-kicker? (rest kicker1) (rest kicker2))
    :else (> (first kicker1) (first kicker2))))

;; 14

(defn beats-case-1 "If the counts are the same, call higher-kicker?, else return the count comparison" [kicker1 kicker2]
  (if (= (count kicker1) (count kicker2))
    (higher-kicker? kicker1 kicker2)
    (> (count kicker1) (count kicker2))))

(defn beats-case-2 "If the values are the same call beats-case-1, else return their comparison" [hand1 hand2 value1 value2]
  (if (= value1 value2)
    (beats-case-1 (kickers hand1) (kickers hand2))
    (> value1 value2)))

(defn beats? "Check if the first hand beats the second one. Using the cases above, check if the hands are equal before proceeding. Return true or nil in case of false" [hand1 hand2]
  (if
   (beats-case-2 hand1 hand2 (value hand1) (value hand2))
    true
    nil))

;; 15

(defn best-hand "Return the best hand" [hand1 hand2]
  (if (beats? hand2 hand1)
    hand2
    hand1))

(defn equal? "Does neither hand beat the other one?" [hand1 hand2]
  (and
   (not (beats? hand1 hand2))
   (not (beats? hand2 hand1))))

(defn best-hands "Get all the hands equal to the best hand" [best hands]
  (filter #(equal? best %) hands))

(defn unwrap "Return the element in the list if it's only one, otherwise return the list" [lst]
  (if (= (count lst) 1)
    (first lst)
    lst))

(defn winning-hand "Get all the best hands, and then unwrap it or them" [& hands]
  (unwrap (best-hands (reduce best-hand hands) hands)))

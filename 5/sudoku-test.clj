(ns user
  (:require [clojure.test :refer :all]
            [user :refer :all]))

;; 1

(defn matrix_1 []
  [[9 0 1 3 0 8 6 0 0]
   [0 6 7 0 0 0 3 0 9]
   [3 8 0 0 6 0 0 0 4]
   [0 0 0 2 8 0 0 4 1]
   [8 0 0 4 0 0 0 2 6]
   [1 2 0 0 9 5 0 0 8]
   [0 0 0 0 0 0 0 6 7]
   [6 0 8 0 0 2 0 0 0]
   [0 0 3 8 0 6 1 5 0]])

(defn matrix_1_transform []
  '((#{9} #{4 5} #{1} #{3} #{7 4 2 5} #{8} #{6} #{7} #{2 5})
    (#{4 2 5} #{6} #{7} #{1 5} #{1 4 2 5} #{1 4} #{3} #{1 8} #{9})
    (#{3} #{8} #{2 5} #{7 1 9 5} #{6} #{7 1 9} #{7 2 5} #{7 1} #{4})
    (#{7 5} #{7 3 9 5} #{6 9 5} #{2} #{8} #{7 3} #{7 9 5} #{4} #{1})
    (#{8} #{7 3 9 5} #{9 5} #{4} #{7 1 3} #{7 1 3} #{7 9 5} #{2} #{6})
    (#{1} #{2} #{4 6} #{7 6} #{9} #{5} #{7} #{7 3} #{8})
    (#{4 2 5} #{1 4 9 5} #{4 2 9 5} #{1 9 5} #{1 4 3 5} #{1 4 3 9} #{4 2 9 8} #{6} #{7})
    (#{6} #{7 1 4 9 5} #{8} #{7 1 9 5} #{7 1 4 3 5} #{2} #{4 9} #{3 9} #{3})
    (#{7 4 2} #{7 4 9} #{3} #{8} #{7 4} #{6} #{1} #{5} #{2})))

(defn matrix_1_solve []
  [[9 4 1 3 2 8 6 7 5]
   [2 6 7 1 5 4 3 8 9]
   [3 8 5 7 6 9 2 1 4]
   [5 7 6 2 8 3 9 4 1]
   [8 3 9 4 1 7 5 2 6]
   [1 2 4 6 9 5 7 3 8]
   [4 5 2 9 3 1 8 6 7]
   [6 1 8 5 7 2 4 9 3]
   [7 9 3 8 4 6 1 5 2]])

;; 2

(defn matrix_2 []
  [[0 2 5 0 0 1 0 0 0]
   [1 0 4 2 5 0 0 0 0]
   [0 0 6 0 0 4 2 1 0]
   [0 5 0 0 0 0 3 2 0]
   [6 0 0 0 2 0 0 0 9]
   [0 8 7 0 0 0 0 6 0]
   [0 9 1 5 0 0 6 0 0]
   [0 0 0 0 7 8 1 0 3]
   [0 0 0 6 0 0 5 9 0]])

(defn matrix_2_transform []
  '((#{7 3 9 8} #{2} #{5} #{7 3 9 8} #{6 3 9 8} #{1} #{7 4 9 8} #{7 4 3 8} #{7 4 6 8})
    (#{1} #{7 3} #{4} #{2} #{5} #{7 6 3 9} #{7 9 8} #{7 3 8} #{7 6 8})
    (#{7 3 9 8} #{7 3} #{6} #{7 3 9 8} #{3 9 8} #{4} #{2} #{1} #{7 5 8})
    (#{4 9} #{5} #{9} #{7 1 4 9 8} #{1 4 6 9 8} #{7 6 9} #{3} #{2} #{7 1 4 8})
    (#{6} #{1 4 3} #{3} #{7 1 4 3 8} #{2} #{7 3 5} #{7 4 8} #{7 4 5 8} #{9})
    (#{4 3 2 9} #{8} #{7} #{1 4 3 9} #{1 4 3 9} #{3 9 5} #{4} #{6} #{1 4 5})
    (#{7 4 3 2 8} #{9} #{1} #{5} #{4 3} #{3 2} #{6} #{7 4 8} #{7 4 2 8})
    (#{4 2 5} #{4 6} #{2} #{4 9} #{7} #{8} #{1} #{4} #{3})
    (#{7 4 3 2 8} #{7 4 3} #{3 2 8} #{6} #{1 4 3} #{3 2} #{5} #{9} #{7 4 2 8})))

(defn matrix_2_solve []
  [[8 2 5 7 6 1 9 3 4]
   [1 3 4 2 5 9 7 8 6]
   [9 7 6 8 3 4 2 1 5]
   [4 5 9 1 8 6 3 2 7]
   [6 1 3 4 2 7 8 5 9]
   [2 8 7 3 9 5 4 6 1]
   [3 9 1 5 4 2 6 7 8]
   [5 6 2 9 7 8 1 4 3]
   [7 4 8 6 1 3 5 9 2]])

;; 3

(defn matrix_3 []
  [[0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]])

(defn matrix_3_transform []
  '((#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})
    (#{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8}
     #{7 1 4 6 3 2 9 5 8})))

(defn matrix_3_solve []
  [[7 1 4 6 3 2 9 5 8]
   [6 3 2 9 5 8 7 1 4]
   [9 5 8 7 1 4 6 3 2]
   [1 7 6 4 2 3 5 8 9]
   [4 2 3 5 8 9 1 7 6]
   [5 8 9 1 7 6 4 2 3]
   [3 4 7 2 6 1 8 9 5]
   [2 6 1 8 9 5 3 4 7]
   [8 9 5 3 4 7 2 6 1]])

;; 4

(defn matrix_4 []
  [[5 0 0 7 2 0 0 6 4]
   [4 0 2 0 5 0 7 8 9]
   [0 0 7 0 0 0 0 2 5]
   [9 0 6 0 3 4 2 0 8]
   [8 2 1 9 7 0 0 4 0]
   [0 0 0 8 1 0 0 0 6]
   [0 0 4 0 8 5 0 0 2]
   [0 0 0 2 0 0 0 3 7]
   [0 0 3 6 9 0 0 0 0]])

(defn matrix_4_transform []
  '((#{5} #{1 3 9 8} #{9 8} #{7} #{2} #{1 3 9 8} #{1 3} #{6} #{4})
    (#{4} #{1 6 3} #{2} #{1 3} #{5} #{1 6 3} #{7} #{8} #{9})
    (#{1 6 3} #{1 6 3 9 8} #{7} #{1 4 3} #{4 6} #{1 6 3 9 8} #{1 3} #{2} #{5})
    (#{9} #{7 5} #{6} #{5} #{3} #{4} #{2} #{7 1 5} #{8})
    (#{8} #{2} #{1} #{9} #{7} #{6} #{3 5} #{4} #{3})
    (#{7 3} #{7 4 3 5} #{5} #{8} #{1} #{2} #{3 9 5} #{7 9 5} #{6})
    (#{7 1 6} #{7 1 6 9} #{4} #{1 3} #{8} #{5} #{1 6 9} #{1 9} #{2})
    (#{1 6} #{1 6 9 5 8} #{9 5 8} #{2} #{4} #{1} #{1 4 6 9 5 8} #{3} #{7})
    (#{7 1 2} #{7 1 5 8} #{3} #{6} #{9} #{7 1} #{1 4 5 8} #{1 5} #{1})))

(defn matrix_4_solve []
  [[5 3 8 7 2 9 1 6 4]
   [4 6 2 1 5 3 7 8 9]
   [1 9 7 4 6 8 3 2 5]
   [9 7 6 5 3 4 2 1 8]
   [8 2 1 9 7 6 5 4 3]
   [3 4 5 8 1 2 9 7 6]
   [7 1 4 3 8 5 6 9 2]
   [6 5 9 2 4 1 8 3 7]
   [2 8 3 6 9 7 4 5 1]])

;; 5

(defn matrix_5 []
  [[6 2 5 1 9 4 7 8 3]
   [1 8 3 7 6 2 5 9 4]
   [7 9 4 8 3 5 2 1 6]
   [5 3 2 9 1 8 6 4 7]
   [8 6 1 3 4 7 9 2 5]
   [4 7 9 2 5 6 8 3 1]
   [2 5 8 4 7 1 3 6 9]
   [9 4 6 5 2 3 1 7 8]
   [3 1 7 6 8 9 4 5 2]])

(defn matrix_5_transform []
  '((#{6} #{2} #{5} #{1} #{9} #{4} #{7} #{8} #{3})
    (#{1} #{8} #{3} #{7} #{6} #{2} #{5} #{9} #{4})
    (#{7} #{9} #{4} #{8} #{3} #{5} #{2} #{1} #{6})
    (#{5} #{3} #{2} #{9} #{1} #{8} #{6} #{4} #{7})
    (#{8} #{6} #{1} #{3} #{4} #{7} #{9} #{2} #{5})
    (#{4} #{7} #{9} #{2} #{5} #{6} #{8} #{3} #{1})
    (#{2} #{5} #{8} #{4} #{7} #{1} #{3} #{6} #{9})
    (#{9} #{4} #{6} #{5} #{2} #{3} #{1} #{7} #{8})
    (#{3} #{1} #{7} #{6} #{8} #{9} #{4} #{5} #{2})))

(defn matrix_5_solve []
  [[6 2 5 1 9 4 7 8 3]
   [1 8 3 7 6 2 5 9 4]
   [7 9 4 8 3 5 2 1 6]
   [5 3 2 9 1 8 6 4 7]
   [8 6 1 3 4 7 9 2 5]
   [4 7 9 2 5 6 8 3 1]
   [2 5 8 4 7 1 3 6 9]
   [9 4 6 5 2 3 1 7 8]
   [3 1 7 6 8 9 4 5 2]])

;; Tests

(deftest transform_test
  (is (= (transform (matrix_2)) (matrix_2_transform)))
  (is (= (transform (matrix_1)) (matrix_1_transform)))
  (is (= (transform (matrix_3)) (matrix_3_transform))) ;; only 0s
  (is (= (transform (matrix_4)) (matrix_4_transform)))
  (is (= (transform (matrix_5)) (matrix_5_transform)))) ;; fully solved

(deftest solve_test
  (is (= (solve (matrix_2)) (matrix_2_solve)))
  (is (= (solve (matrix_1)) (matrix_1_solve)))
  (is (= (solve (matrix_3)) (matrix_3_solve))) ;; only 0s
  (is (= (solve (matrix_4)) (matrix_4_solve)))
  (is (= (solve (matrix_5)) (matrix_5_solve)))) ;; fully solved

(run-tests)

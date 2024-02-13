(ns user
  (:require [clojure.set :refer [difference union]]
            [clojure.string :refer [join]]))

;; Initial puzzle state

(defn initial_matrix []
  [[0 2 5 0 0 1 0 0 0]
   [1 0 4 2 5 0 0 0 0]
   [0 0 6 0 0 4 2 1 0]
   [0 5 0 0 0 0 3 2 0]
   [6 0 0 0 2 0 0 0 9]
   [0 8 7 0 0 0 0 6 0]
   [0 9 1 5 0 0 6 0 0]
   [0 0 0 0 7 8 1 0 3]
   [0 0 0 6 0 0 5 9 0]])

;; Helper functions

;; m - matrix
;; x, y - coordinates
;; v - value
;; sx, sy - square coordinates
;; t - transformed matrix

(defn get_number "Get number by coordinates" [m x y]
  (nth (nth m y) x))

(defn get_column "Get column by index" [m x]
  (map #(get_number m x %) (range 9)))

(defn get_row "Get row by index" [m x]
  (nth m x))

(defn get_column_used_values "Get used values in a column by index" [m x]
  (set (get_column m x)))

(defn get_row_used_values "Get used values in a row by index" [m y]
  (set (get_row m y)))

(defn get_square_used_values "Get used values in a square" [m sx sy]
  (set (map
        #(get_number m (+ (* 3 sx) (first %)) (+ (* 3 sy) (second %)))
        (for [x (range 3) y (range 3)] [x y]))))

(defn get_used_values "Get used values for coordinates" [m x y]
  (union
   (get_row_used_values m y)
   (get_column_used_values m x)
   (get_square_used_values m (quot x 3) (quot y 3))))

(defn get_unused_values "Get unused values for coordinates" [m x y]
  (if (> (get_number m x y) 0)
    (set [(get_number m x y)])
    (difference
     (set (range 10))
     (get_used_values m x y))))

(defn non_nil_value "Find the first value in the collection not equal to nil" [coll]
  (first (filter (complement nil?) coll)))

(defn valid? "Check if a value is valid in the matrix by coordinates" [m x y v]
  (not (contains? (get_used_values m x y) v)))

(defn solve_for_position "Get valid values for given position" [m t x y]
  (filter #(valid? m x y %) (get_number t x y)))

(defn fill_value "Create a new matrix with a single changed value by coordinates" [m x y v]
  (assoc m y (assoc (get_row m y) x v)))

(defn solver "Get the first solution for the matrix" [m t x y]
  (cond
    (= y 9) m
    (= x 9) (solver m t 0 (inc y))
    (> (get_number m x y) 0) (solver m t (inc x) y)
    :else (non_nil_value
           (map
            #(solver (fill_value m x y %) t (inc x) y)
            (solve_for_position m t x y)))))

(defn get_row_unused_values "Get sets of unused values for each position in a row by index" [m x]
  (map #(get_unused_values m % x) (range 9)))

(defn print_matrix "Print matrix" [m]
  (doall (map #(println (join "" %)) m)))

;; 2

(defn transform "Get sets of unused values for each position in the matrix" [m]
  (map #(get_row_unused_values m %) (range 9)))

;; 1

(defn solve "Calls the solver with the transformed matrix and starting coordinates at 0, 0" [m]
  (solver m (transform m) 0 0))

(println "Initial:")
(print_matrix (initial_matrix))

(println "\n")
(println "Solved:")
(print_matrix (solve (initial_matrix)))

:end

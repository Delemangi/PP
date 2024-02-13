(ns user
  (:require [clojure.string :refer [join]]))

;; Matrix

(def matrix '[[0, 2, 1, 3]
              [2, 3, 1, 0]
              [3, 1, 2, 0]])

;; Functions

(defn slide "Slide list once" [lst]
  (into [] (concat [(last lst)] (drop-last 1 lst))))

(defn slide-n "Slide list N times" [n lst]
  (if (= n 0)
    lst
    (slide-n (dec n) (slide lst))))

(defn get-row "Get row by index" [matrix x]
  (nth matrix x))

(defn get-position "Get value by coordinates" [matrix x y]
  (nth (nth matrix y) x))

(defn get-column "Get column by index" [matrix x]
  (map #(get-position matrix x %) (range (count matrix))))

(defn valid? "Does the matrix have a valid solution?" [matrix x]
  (if (= x (count (first matrix)))
    true
    (and
     (= (count (set (get-column matrix x))) (count (get-column matrix x)))
     (valid? matrix (inc x)))))

(defn not-nil "Get the first value not equal to nil" [coll]
  (first (filter (complement nil?) coll)))

(defn set-row "Change row and return matrix" [matrix y row]
  (assoc matrix y row))

(defn solve-helper "Find a solution by recursing" [matrix y]
  (if (= y (count matrix))
    (if (valid? matrix 0) matrix nil)
    (not-nil
     (map
      #(solve-helper (set-row matrix y (slide-n % (get-row matrix y))) (inc y))
      (range (count (first matrix)))))))

(defn solve "Solve starting from the top" [matrix]
  (solve-helper matrix 0))

(defn print-matrix "Visualize the matrix" [matrix]
  (doall (map #(println (join "" %)) matrix)))

(println "Initial:")
(print-matrix matrix)

(println "\nSolved:")
(print-matrix (solve matrix))

:end

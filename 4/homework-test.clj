(ns user
  (:require [clojure.test :refer :all]
            [user :refer :all]))

;; 1

;; А

(deftest atomic?-test
  (is (= (atomic? 5) true))
  (is (= (atomic? '(1 2 3)) false))
  (is (= (atomic? '()) false))
  (is (= (atomic? '[]) false))
  (is (= (atomic? nil) true)))

;; Б

(deftest member?-test
  (is (= (member? 1 '(1 1 2 3)) true))
  (is (= (member? 1 '(1 2 3)) true))
  (is (= (member? '() '()) false))
  (is (= (member? nil '()) false))
  (is (= (member? 2 '(1 2 3)) true)))

;; В

(deftest my-count-test
  (is (= (my-count '(a b () d)) 4))
  (is (= (my-count '(a (b c) (d e) (nil))) 4))
  (is (= (my-count '(a () () ())) 4))
  (is (= (my-count '(a (b c (d e)) f)) 3))
  (is (= (my-count '((a b c d))) 1)))

;; Г

(deftest append-test
  (is (= (append '(:a :b :c) '(1 (2 3))) '(:a :b :c 1 (2 3))))
  (is (= (append '(1 (2 3) :d) '(:a :b :c)) '(1 (2 3) :d :a :b :c)))
  (is (= (append '(1 (2 3)) '()) '(1 (2 3))))
  (is (= (append '() '()) '()))
  (is (= (append '(1 (2 3)) '(:a :b :c)) '(1 (2 3) :a :b :c))))

;; Д

(deftest zip-test
  (is (= (zip '() '()) '()))
  (is (= (zip '(:a :b :c) '(() (2 3) nil ())) '((:a ()) (:b (2 3)) (:c nil))))
  (is (= (zip '(:a :b :c) '(1 (2 3) 4 5)) '((:a 1) (:b (2 3)) (:c 4))))
  (is (= (zip '(:a :b :c :d :e) '(1 (2 3) 4)) '((:a 1) (:b (2 3)) (:c 4))))
  (is (= (zip '((:a :d) nil :c) '(1 (2 3) 4 5)) '(((:a :d) 1) (nil (2 3)) (:c 4)))))

;; Ѓ

(deftest lookup-test
  (is (= (lookup :b '((:a 1) (:b 2) (:c 3))) 2))
  (is (= (lookup :a '((:a 1) (:b 2) (:c 3))) 1))
  (is (= (lookup nil '()) nil))
  (is (= (lookup :d '((:a 1) (:b 2) (:c 3))) nil))
  (is (= (lookup :c '((:a 1) (:b 2) (:c 3))) 3)))

;; Е

(deftest my-merge-test
  (is (= (my-merge '(1 3 5) '(2 4)) '(1 2 3 4 5)))
  (is (= (my-merge '(3 7 12 19 19 25 30) '(4 7 10 12 20)) '(3 4 7 7 10 12 12 19 19 20 25 30)))
  (is (= (my-merge '(1 3 5) '()) '(1 3 5)))
  (is (= (my-merge '() '()) '()))
  (is (= (my-merge '() '(2 4)) '(2 4))))

;; Ж

(deftest count-all-test
  (is (= (count-all '(a (b c () (25) nil) ())) 5))
  (is (= (count-all '((((nil nil))) nil)) 3))
  (is (= (count-all '(((()) ()) ())) 0))
  (is (= (count-all '()) 0))
  (is (= (count-all '((((1))))) 1)))

;; З

(deftest my-drop-test
  (is (= (my-drop 7 '(a b c d e)) '()))
  (is (= (my-drop 1 '(a b c d e)) '(b c d e)))
  (is (= (my-drop 2 '(a b c d e)) '(c d e)))
  (is (= (my-drop 5 '(a b c d e)) '()))
  (is (= (my-drop 0 '(a b c d e)) '(a b c d e))))

;; Ѕ

(deftest my-take-test
  (is (= (my-take 7 '(a b c d e)) '(a b c d e)))
  (is (= (my-take 5 '(a b c d e)) '(a b c d e)))
  (is (= (my-take 2 '(a b c d e)) '(a b)))
  (is (= (my-take 1 '(a b c d e)) '(a)))
  (is (= (my-take 0 '(a b c d e)) '())))

;; И

(deftest my-reverse-test
  (is (= (my-reverse '(() (1 2) ((3 4) 5))) '(((3 4) 5) (1 2) ())))
  (is (= (my-reverse '(1 2)) '(2 1)))
  (is (= (my-reverse '(nil)) '(nil)))
  (is (= (my-reverse '(1 2 3 4 5)) '(5 4 3 2 1)))
  (is (= (my-reverse '()) '())))

;; Ј

(deftest remove-duplicates-test
  (is (= (remove-duplicates '(1 2 1 2 1 2 1 1)) '(1 2)))
  (is (= (remove-duplicates '(nil () nil ())) '(nil ())))
  (is (= (remove-duplicates '(1 2 2 3 1)) '(1 2 3)))
  (is (= (remove-duplicates '()) '()))
  (is (= (remove-duplicates '(1 (1) 1 (1 1) ((1)))) '(1 (1) (1 1) ((1))))))

;; К

(deftest my-flatten-test
  (is (= (my-flatten '(() () ())) '()))
  (is (= (my-flatten '(((nil)))) '((nil))))
  (is (= (my-flatten '(((1 1) (2 3)) ((5 7)))) '((1 1) (2 3) (5 7))))
  (is (= (my-flatten '((1 2 (3 (4)) 5) (6) ())) '(1 2 (3 (4)) 5 6)))
  (is (= (my-flatten '(((1 2) (3 4)) (5 6))) '((1 2) (3 4) 5 6))))

;; 2

;; А

(deftest buzz-test
  (is (= (buzz '(7 13 14 15 20 21 22)) '(:buzz 13 :buzz 15 20 :buzz 22)))
  (is (= (buzz '(1 2 3 7 8)) '(1 2 3 :buzz 8)))
  (is (= (buzz '()) '()))
  (is (= (buzz '(69 70 71 72 77 107)) '(69 :buzz :buzz :buzz :buzz :buzz)))
  (is (= (buzz '(17)) '(:buzz))))

;; Б

(deftest divisors-of-test
  (is (= (divisors-of 16) '(2 4 8)))
  (is (= (divisors-of 13) '()))
  (is (= (divisors-of 2) '()))
  (is (= (divisors-of 60) '(2 3 4 5 6 10 12 15 20 30)))
  (is (= (divisors-of 12) '(2 3 4 6))))

;; В

(deftest longest-list-of-strings-test
  (is (= (longest-list-of-strings '("")) ""))
  (is (= (longest-list-of-strings '("123", "456")) "123"))
  (is (= (longest-list-of-strings '("456" "123")) "456"))
  (is (= (longest-list-of-strings '("1", "aaa", "22", "333")) "aaa"))
  (is (= (longest-list-of-strings '("1", "333", "22")) "333")))

;; 3

;; А

(deftest my-map-test
  (is (= (my-map dec '(1 2 3)) '(0 1 2)))
  (is (= (my-map inc '(1 2 3)) '(2 3 4)))
  (is (= (my-map #(* % 2) '(1 2 3)) '(2 4 6)))
  (is (= (my-map inc '()) '()))
  (is (= (my-map #(rem % 2) '(1 2 3)) '(1 0 1))))

;; Б

(deftest my-filter-test
  (is (= (my-filter odd? '(1 2 3 4 5)) '(1 3 5)))
  (is (= (my-filter even? '(1 2 3 4 5)) '(2 4)))
  (is (= (my-filter #(= % "1") '("a", "b", "1", "2")) '("1")))
  (is (= (my-filter pos? '(-2 -1 0 1 2)) '(1 2)))
  (is (= (my-filter even? '()) '())))

;; В

(deftest my-reduce-test
  (is (= (my-reduce min '(1 2 3)) 1))
  (is (= (my-reduce * '(2 2 3)) 12))
  (is (= (my-reduce concat '("h" "el" "lo")) (seq "hello")))
  (is (= (my-reduce + '(1 2 3)) 6))
  (is (= (my-reduce max '(1 2 3)) 3)))

;; Г

(deftest my-flat-map-test
  (is (= (my-flat-map #(list % %) '(1 2 3)) '(1 1 2 2 3 3)))
  (is (= (my-flat-map #(list %) '(1 2 3)) '(1 2 3)))
  (is (= (my-flat-map #(list % (inc %)) '(1 2 3)) '(1 2 2 3 3 4)))
  (is (= (my-flat-map #(list % (* % 2)) '(1 2 3)) '(1 2 2 4 3 6)))
  (is (= (my-flat-map #(vector % (dec %)) '(2 3 4)) '(2 1 3 2 4 3))))

(run-tests)

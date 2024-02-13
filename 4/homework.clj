(ns user)

;; 1

;; А
(defn atomic? "Atomic is the opposite of collection" [v]
  (not (coll? v)))

;; Б
(defn member? "Check if one of the elements in lst is equal to x" [x lst]
  (boolean (some #(= x %) lst)))

;; В
(defn my-count "Count all function calls while recursing through lst" [lst]
  (if (empty? lst)
    0
    (+ 1 (my-count (rest lst)))))

;; Г
(defn append "Concatenate two lists" [lst1 lst2]
  (concat lst1 lst2))

;; Д
(defn zip "Pair up the elements from both lists into vectors" [lst1 lst2]
  (map vector (take (min (count lst1) (count lst2)) lst1) lst2))

;; Ѓ
(defn lookup "Filter the pairs in which first is the key, then get the first such pair, then get the second element"
  [key list-of-pairs]
  (second
   (first
    (filter
     (fn [pair] (= key (first pair)))
     list-of-pairs))))

;; Е
(defn my-merge "Concatenate then sort the lists" [lst1 lst2]
  (sort (concat lst1 lst2)))

;; Ж
(defn count-all "Recurse through all elements of lst, counting all function calls, unless the element is a list, then recurse through all its elements too" [lst]
  (if (= (count lst) 0)
    0
    (+
     (if (coll? (first lst)) (count-all (first lst)) 1)
     (count-all (rest lst)))))

;; З
(defn my-drop "Decrement n and call rest on lst until n is 0 or less" [n lst]
  (if (<= n 0)
    lst
    (my-drop (dec n) (rest lst))))

;; Ѕ
(defn my-take "Reverse, drop length - n elements, reverse again" [n lst]
  (reverse (my-drop (max (- (count lst) n) 0) (reverse lst))))

;; И
(defn my-reverse "Append the reverse of the rest of the elements with the first element" [lst]
  (if (empty? lst)
    '()
    (append (my-reverse (rest lst)) [(first lst)])))

;; Ј
(defn remove-duplicates "Use built in distinct function" [lst]
  (distinct lst))

;; К
(defn my-flatten "Concatenate all of the elements in the list" [lst]
  (apply concat lst))

;; 2

;; А

(defn buzz "Check if each element is divisible by 7 or contains '7', and replace it with 'buzz' if so" [list-of-ints]
  (map (fn [n] (if (or (zero? (mod n 7)) (some #(= \7 %) (str n)))
                 :buzz
                 n))
       list-of-ints))

;; Б
(defn divisors-of "Filter numbers between 1 and n that divide n" [n]
  (filter (fn [x] (and (> x 1) (< x n) (zero? (mod n x))))
          (range 1 (inc n))))

;; В
(defn longest-list-of-strings "Iterate all strings while checking the length of each, and keep the first one with a greater length than all previous strings" [list-of-strings]
  (reduce (fn [a b] (if (> (count b) (count a)) b a)) list-of-strings))

;; 3

;; А
(defn my-map "Call the function on the first element and append the recursive call of the function to it" [f lst]
  (if (empty? lst)
    '()
    (append [(f (first lst))] (my-map f (rest lst)))))

;; Б
(defn my-filter "If the predicate is true on the first element, then add to it the recursive call, or continue with the rest" [pred lst]
  (cond
    (empty? lst) '()
    (pred (first lst))
    (append [(first lst)] (my-filter pred (rest lst)))
    :else (my-filter pred (rest lst))))

;; В
(defn my-reduce "With 2 args, pass the first element as value and the rest as lst. With 3 args, pass the result of the function call to value and the first element, and pass the rest of the list. Stop and return the value when the list is empty"
  ([f lst]
   (my-reduce f (first lst) (rest lst)))
  ([f value lst]
   (if (empty? lst)
     value
     (my-reduce f (f value (first lst)) (rest lst)))))

;; Г
(defn my-flat-map "Apply the function f until rest becomes empty, and then return acc. Recur with the tail of rest and the result of the mapping merged into acc" [f lst]
  (if (empty? lst)
    '()
    (concat (f (first lst)) (my-flat-map f (rest lst)))))

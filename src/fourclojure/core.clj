(ns fourclojure.core (:use clojure.test))

(deftest number-9
  (testing
    "When operating on a set, the conj function returns a new set with
     one or more keys 'added'."

    (is (= #{1 2 3 4} (conj #{1 4 3} 2)))))

(deftest number-10
  (testing
    "Maps store key-value pairs. Both maps and keywords can be used as
     lookup functions. Commas can be used to make maps more readable, but
     they are not required."

    (is (= 20 ((hash-map :a 10, :b 20, :c 30) :b))
    (is (= 20 (:b  {:a 10, :b 20, :c 30}))))))

(deftest number-11
  (testing
    "When operating on a map, the conj function returns a new map with
     one or more key-value pairs 'added'."

    (is (= {:a 1, :b 2, :c 3} (conj {:a 1} [:b 2] [:c 3])))))

(deftest number-12
  (testing
    "All Clojure collections support sequencing. You can operate on
     sequences with functions like first, second, and last."

    (is (= 3 (first '(3 2 1))))
    (is (= 3 (second [2 3 4])))
    (is (= 3 (last (list 1 2 3))))))

(deftest number-13
  (testing
    "The rest function will return all the items of a sequence except
    the first."

    (is (= [20 30 40] (rest [10 20 30 40])))))

(deftest number-14
  (testing
    "Clojure has many different ways to create functions."

    (is (= 8 ((fn add-five [x] (+ x 5)) 3)))
    (is (= 8 ((fn [x] (+ x 5)) 3)))
    (is (= 8 (#(+ % 5) 3)))
    (is (= 8 ((partial + 5) 3)))))

(deftest number-15
  (testing
    "Write a function which doubles a number."

    (let [doubler (fn [n] (* n 2))]
      (is (= (doubler 2) 4))
      (is (= (doubler 3) 6))
      (is (= (doubler 11) 22))
      (is (= (doubler 7) 14)))))

(deftest number-16
  (testing
    "Write a function which returns a personalized greeting."

    (let [greeting (fn [name] (str "Hello, " name "!"))]
      (is (= (greeting "Dave") "Hello, Dave!"))
      (is (= (greeting "Jenn") "Hello, Jenn!"))
      (is (= (greeting "Rhea") "Hello, Rhea!")))))

(deftest number-17
  (testing
    "The map function takes two arguments: a function (f) and a sequence
    (s). Map returns a new sequence consisting of the result of applying
    f to each item of s. Do not confuse the map function with the map data
    structure."

    (is (=  '(6 7 8) (map #(+ % 5) '(1 2 3))))))

(deftest number-18
  (testing
    "The filter function takes two arguments: a predicate function (f) and a
    sequence (s). Filter returns a new sequence consisting of all the items of
    s for which (f item) returns true."

    (is (= '(6 7) (filter #(> % 5) '(3 4 5 6 7))))))
    ;; Greater than *this number* *that number*
    ;; Read as: "Is this number greater than that number?"

(deftest number-19
  (testing
    "Write a function which returns the last element in a sequence. ** Do not use last"
    ;;
    ;; Some better solutions include:
    ;; (comp first reverse)
    ;; (fn [x] (first (reverse x)))
    ;;
    (let [get-last (fn [coll] (get (vec coll) (- (count coll) 1)))]
      (is (= (get-last [1 2 3 4 5]) 5))
      (is (= (get-last '(5 4 3)) 3))
      (is (= (get-last ["b" "c" "d"]) "d")))))

(deftest number-20
  (testing
    "Write a function which returns the second to last element from a sequence."
    ;;
    ;; Alternate solutions include:
    ;; (comp peek pop vec)
    ;;   peek: For a vector, returns last. For a list or queue, first.
    ;;   pop:  For a vector, new list without last item. For a list or queue,
    ;;         new list/queue without the first item.
    ;;
    ;; #(second (reverse %))
    ;; (fn [x] (first (rest (reverse x))))
    ;;
    (let [get-penultimate (comp first next reverse vec)]
      (is (= (get-penultimate (list 1 2 3 4 5)) 4))
      (is (= (get-penultimate ["a" "b" "c"]) "b"))
      (is (= (get-penultimate [[1 2] [3 4]]) [1 2])))))

(deftest number-21
  (testing
    "Write a function which returns the Nth element from a sequence.
    ** Do not use nth"
    ;;
    ;; Alternate solutions include:
    ;; (fn self [xs i]
    ;;   (cond
    ;;     (= i 0) (first xs)
    ;;     :else   (self (rest xs) (- i 1)))
    ;;   Solves it recursively!
    ;;
    ;; #(first (drop %2 %))
    ;;   drop: Drops all the first n items from the coll - actually
    ;;         it returns a lazy sequence without those items
    ;;
    (let [my-nth (fn [coll n] (get (vec coll) n))]
      (is (= (my-nth '(4 5 6 7) 2) 6))
      (is (= (my-nth [:a :b :c] 0) :a))
      (is (= (my-nth [1 2 3 4] 1) 2))
      (is (= (my-nth '([1 2] [3 4] [5 6]) 2) [5 6])))))

(deftest number-22
  (testing "Write a function which returns the total number of elements in a
           sequence. ** Do not use count"
    ;;
    ;; Just use reduce!
    ;; reduce (fn [n _] (inc n)) 0
    ;;
    (let [count-it (fn [coll]
            (loop [n 0 remaining (vec coll)]
              (if-not (seq remaining) n (recur (inc n) (rest remaining)))))]
      (is (= (count-it '(1 2 3 3 1)) 5))
      (is (= (count-it "Hello World") 11))
      (is (= (count-it [[1 2] [3 4] [5 6]]) 3))
      (is (= (count-it '(13)) 1))
      (is (= (count-it '(:a :b :c)) 3)))))

(deftest number-23
  (testing "Write a function which reverses a sequence."
    ;;
    ;; Just use reduce and conj!
    ;; reduce conj ()
    ;; or apply and conj
    ;; apply conj ()
    ;;
    ;; conj with a list: prepends
    ;; conj with a vector: append
    ;; conj is variadic - it can take any number of arguments, whereas
    ;; cons cannot. That is why the seq comes first for conj. cons
    ;; works as you expect for lists, and conj works as you expected
    ;; for vectors.
    ;;
    ;; Note that in these cases, reduce just takes some function that accepts
    ;; two arguments in the expected order: result, item. Conj works for this because
    ;; the collection (result) comes first, and then the item
    ;;
    (let [reverse-it (partial reduce conj ())]
      (is (= (reverse-it [1 2 3 4 5]) [5 4 3 2 1] ))
      (is (= (reverse-it (sorted-set 5 7 2 7)) '(7 5 2)))
      ; (is (= (reverse-it ([[1 2][3 4][5 6]]  [[5 6][3 4][1 2]]))))
      )))

;; 24 is too easy: just apply + or reduce +
;; 25 is too easy: just filter odd?

(deftest number-26
  (testing "Write a function which returns the first X fibonacci numbers."
    ;;
    ;; This one was interesting. The actual solution I submitted was this:
    ;; (letfn [(fib [n] (if (<= n 1)
    ;;                       1
    ;;                       (+ (fib (dec n)) (fib (- n 2)))))]
    ;;    #(apply list (map fib (range %)))
    ;;
    (letfn [(fib [n] (if (<= n 1) 1 (+ (fib (dec n)) (fib (- n 2)))))
            (fib-seq [n] (apply list (map fib (range n))))]
      (is (= (fib-seq 3) '(1 1 2)))
      (is (= (fib-seq 6) '(1 1 2 3 5 8)))
      (is (= (fib-seq 8) '(1 1 2 3 5 8 13 21))))))

(deftest number-27
  (testing "Write a function which returns true if the given sequence is a palindrome."
    (letfn [(palindrome? [value] (= (seq value) (reverse value)))]
      (is (false? (palindrome? '(1 2 3 4 5))))
      (is (true?  (palindrome? "racecar"))))))


(deftest number-35
  (testing "Clojure lets you give local names to values using the special let-form."
    (is (= 7 (let [x 5] (+ 2 x))))
    (is (= 7 (let [x 3 y 10] (- y x))))
    (is (= 7 (let [x 21] (let [y 3] (/ x y)))))))

(run-tests)

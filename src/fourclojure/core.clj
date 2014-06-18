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

(run-tests)

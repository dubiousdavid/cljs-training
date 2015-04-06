(ns training.day1
  "Types, special forms, equality, logic, let bindings,
  named functions.")

; Types
"Billy"
\b
:name
36
36.1
[1 2 3]
{:name "David" "age" 37}
#{1 2 3}
'(1 2 3)
#"[0-9]+"
true
false
nil

; Prefix functions
; First form: function, macro, special form

; Special forms
(def x 1)

(if true 1 2)

(do
  (println "hi")
  3)

(let [x 1
      y 2]
  (+ x y))

(fn [x] (inc x))

(loop [x 0]
  (if (< x 2)
    (recur (inc x))
    x))

(try
  (/ 2 0)
  (catch Exception e
    (println e)))
      

; Equality
(= 1 1)
(= 1)
(= 1 2)
(= 2 2 2 2)
(= [1 2] [1 2])
(= [1 2] [2 1])
(= {:name "David"} {:name "David"})
(= {:name "David"} {:name "Bob"})
(= {:name "David" :age 35}
   {:age 35 :name "David"})
(= #{1 2} #{1 2})
(= #{1 2} #{2 1})
(= [1 [2 3]] [1 [2 3]])
(= "Billy" "Billy")
(= "Billy" "billy")
(= "Billy" 2)
(= 1 nil)
(= nil nil)
(= true true)
(= false false true)
(= 1 1.0)
; == type independent numeric equality check
(== 1 1)
; Commas are whitespace
(= [1 2 3] [1, 2, 3])

; Inequality
(not= 1 2)
(not= 1 2 1 2)
(not= 2 2 2)

; Comparison
(> 2 1)
(> 5 4 3)
(< 2 1)
(< 1 2 1)
(>= 4 2)
(<= 4 2)

; Logic

; nil and false are falsey,
; all other values are truthy

; and/or
(and 1 "hi")
(and 1 "hi" true :bob)
(and 1 2 3 false 4 5)
(and 1 2 3 nil 4 5)
(and)

(or nil true)
(or false nil 1)
(or false false 1 false 2)
(or 1 2 3)
(or)

; Predicates
(nil? 1)
(nil? nil)
(true? true)
(true? false)
(false? false)
(false? nil)
(zero? 0)
(even? 2)
(odd? 3)
(empty? [])
(empty? [1])
(contains? [1 2] 1)
(vector? [1 2])
(map? {})
(set? #{"Billy" "Bobby"})
(fn? (fn []))

; Scoping
(def some-number 5)

(let [some-number 10]
  (str "some-number inside let: "
       some-number))

(str "some-number outside let: "
     some-number)

(let [x 5
      y 10]
  (let [x 6]
    [x y]))

(let [x 5
      y 10
      x 6]
  x)

(let [x 10
      _ (println (str "Debug: " x))]
  x)

; Named functions
(defn add-one [x]
  (inc x))

(add-one 1)

(defn add-one
  "Given a number, adds one to that number."
  [x]
  (inc x))

; Example #1
(def score 5)

(if (> score 10)
  "New high score!"
  "Try again")

(when (<= score 10)
  (let [points-away (- 10 score)]
    (println (str points-away " points away"))
    (if (< score 5)
      "You really need to practice"
      "You're really close")))

; Example #2
(defn register [name]
  (format "Registered: %s" name))

(defn load-user []
  (println "Loading..."))

(defn greet [name]
  (str "Hello, " name))

(defn login [status]
  (if (= status :registered)
    (do
      (load-user)
      (greet "Billy"))
    (register "Billy")))

(login :registered)
(login :unregistered)
(ns training.day2
  "cond, condp, case, if-let, threading, seqs,
  vectors, maps, sets, destructuring")

; cond
(def rating 8)

(cond (< rating 2) "Not good"
      (and (>= rating 2) (<= rating 5)) "Decent"
      (and (> rating 5) (<= rating 8)) "Nice job"
      :else "Fantastic")

; condp
(def status :inactive)

(condp = status
  :active "You're active"
  :inactive "You're inactive"
  "You're not in the system")

; case
(defn get-status [name]
  (case name
    "bob" :active
    "joe" :inactive
    nil))

(get-status "bob")

; if-let
(defn print-status [n]
  (if-let [status (get-status n)]
    (str "Your status is " (name status))
    "You're not in the system"))

(print-status "bob")
(print-status "frank")

; when-let
(when-let [status (get-status "joe")]
  (str "Your status is: " status))

; Threading
(-> 1 (inc) (dec) (inc))
(macroexpand '(-> 1 (inc) (dec) (inc)))
(-> 1 inc dec inc)

(->> [1 2 3]
  (map inc)
  (filter even?))

(macroexpand '(->> [1 2 3]
                (map inc)
                (filter even?)))

; Seqs
(map inc [1 2 3])
(type (map inc [1 2 3]))
(range 5)
(take 10 (range))
(def nums (take 10 (range)))
(list 1 2 3)
(seq? nums)
(seq? (list 1 2 3))

; Vectors
(def v [1 2 3 4 5])
(vector :a :b :c)
(vec (range 5))
(conj v 6 7 8)
(into v [6 7 8 9])
(into [] #{1 2 3})
(assoc v 0 -1 1 -2)
(= v [1 2 3 4 5])
(first v)
(second v)
(rest v)
(rest [])
(next v)
(next [])
(vector? v)
(count v)
(empty? [])
(empty v)
(seq [1 2])
(seq [])
(get v 0)
(get v 4)
(get v 10)
(get v 10 "not-found")
(contains? v 0)
(contains? v 10)
(nth v 0)
(nth v 10 :not-found)
(rseq v)
(subvec v 0 2)
(distinct [1 2 3 2 4 1 5])
(when (seq v)
  "Not empty")

; Maps
(def m {:a 1 :b 2})
(hash-map :a 1 :b 2)
(apply hash-map [:a 5 :b 6])
(assoc m :c 3 :d 4 :e 5)
(dissoc m :a)
(seq m)
(seq {})
(into m [[:d 4] [:e 5]])
(into m {:d 4 :e 5})
(zipmap [:name :age :phone :status]
        ["David" 34 "555-555-5555"])
(conj m [:c 3])
(map? m)
(count m)
(empty m)
(empty? {})
(= m {:a 1 :b 2})
(first m)
(second m)
(rest m)
(rest {})
(next m)
(next {})
(keys m)
(vals m)
(get m :a)
(get m :d "not-found")
(:a m)
(:c m 3)
(m :a)

; Sets
(def s #{1 2 3})
(hash-set :a :b :c)
(set "aeiouy")
(set [1 2 3 2 3])
(conj s 4 5 6)
(into s [2 3 4 5])
(set? s)
(= s #{3 2 1})
(get s 1)
(get s 4)
(s 1)
(s 4)

; Keywords, maps, and sets as fns
(def emp1
  {:name "Franky"
   :position "Developer"})

(def emp2
  {:name "Bobby"
   :position "Accountant"})

(:name emp1)
(:position emp1)
(:age emp1)
(emp1 :name)
(emp1 :position)
(emp1 :age)
(map emp1 [:name :position])
(map :name [emp1 emp2])
(map :position [emp1 emp2])

(def statuses
  #{:active :inactive :pending})

(statuses :active)
(statuses :inactive)
(statuses :removed)

; Destructuring

; Vectors
(let [[fst snd] [1 2]]
  fst)

(let [[fst snd thrd] [1 2]]
  thrd)

(let [[fst snd :as v] [1 2 3 4]]
  [fst v])

(let [[[fst-fst] snd] [[1 2] [3 4]]]
  [fst-fst snd])

; Seqs
(let [[fst snd] (map inc (range))]
  snd)

; Don't care '_'
(let [[_ snd] (filter even? (range))]
  snd)

; Maps
(let [{age :age} {:name "Billy"
                  :age 35}]
  age)

(let [{age :age n :name}
      {:name "Billy" :age 35}]
  (str n " is " age " years old"))

(let [{:keys [age name]}
      {:name "Billy" :age 35}]
  (str name " is " age " years old"))

(let [{:keys [age name] :as m}
      {:name "Billy" :age 35}]
  {name m})

(let [{:keys [age name]
       :or {name "Anonymous"}}
      {:age 35}]
  (str name " is " age " years old"))

(let [{:keys [age name]
       :or {name "Anonymous"}}
      {:age 35 :name "Billy"}]
  (str name " is " age " years old"))

; Functions use let
(defn scores [[fst snd]]
  snd)

(scores [99 88 76])

(defn username
  [{:keys [username first-name]}]
  (str first-name " has a username of " username))

(username {:username "BillyBob"
           :first-name "Billy"
           :last-name "Bob"})

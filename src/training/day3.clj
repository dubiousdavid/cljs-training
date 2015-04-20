(ns training.day3
  "Variadic functions, keyword args, anonymous functions,
  for comprehensions, doseq, map, filter, remove, reduce,
  comp, partial, loop/recur"
  (:require [clojure.string :as string]))

; Variadic functions
(defn latest-score [name & scores]
  (format "Latest score for %s is %d" name (last scores)))

(latest-score "Bobby" 88 99 92)

; Keyword arguments
(defn http-req
  "Make an HTTP Request"
  [url & {:keys [timeout follow-redirects]
          :or {timeout 5, follow-redirects true}}]
  (str "Requesting " url " with timeout of " timeout " and "
       (if follow-redirects
         "following redirects"
         "not following redirects")))

(http-req "http://google.com" :timeout 10)
(http-req "http://google.com"
          :timeout 10
          :follow-redirects false)

; Private functions
(defn- local-fn []
  (str "I'm to only be called
       within the defining namespace."))

; Anonymous functions
(fn [x] (+ x 5))

((fn [x] (+ x 5)) 5)

(map #(+ 5 %) (range 5))

; for comprehension
(for [x (range 5)] x)

(for [x (range 5)
      y (range 0 10 2)]
  [x y])

(for [x (range 5)
      y (range 0 10 2)
      :let [sum (+ x y)]]
  (str "sum is: " sum))

(def statuses
  [{:job 1, :status :done}
   {:job 2, :status :started}
   {:job 3, :status :aborted}
   {:job 4, :status :pending}
   {:job 5, :status :done}])

(for [{s :status, j :job} statuses
      :when (= s :done)]
  j)

(for [{s :status, j :job} statuses
      :while (<= j 3)]
  s)

(defn indexed
  "Returns a lazy sequence of [index, item] pairs,
  where items come from s and indexes count up from zero.

  (indexed '(a b c d)) => ([0 a] [1 b] [2 c] [3 d])"
  [s]
  (map-indexed vector s))

(for [[index v] (indexed ["Billy" "Bobby" "Joey"])
      :when (<= index 1)]
  v)

(def user-likes
  {"Billy" [:cycling :swimming]
   "Bobby" [:movies :reading]
   "Franky" [:programming :science]})

(for [[user likes] user-likes
      like likes]
  [user like])

(def athletic-type
  #{:cycling :running :swimming})

(def intellectual-type
  #{:programming :mathematics :science})

(for [[user likes] user-likes
      like likes
      :when (athletic-type like)]
  [user like])

; doseq (like for, but for side-effects)
(doseq [x [1 2 3]
        y [1 2 3]]
  (println x y))

; dotimes (side-effects)
(dotimes [x 5]
  (println x))

; map
(map + [1 2 3] [4 5 6])

(map {:active "Active"
      :inactive "Inactive"
      :pending "Pending"}
     [:active :active :pending])

; filter
(defn rand-bool []
  (even? (rand-int 100)))

(filter even? [0 1 2 4 5 10])
(filter true? [(rand-bool) (rand-bool)])

; remove
(remove string/blank?
        ["David" "" "Billy" "  " nil "Franky"])

; reduce
(reduce + [1 2 3 4 5])
(reduce + 0 [1 2 3 4 5])
(reduce + 0 [])

(def users
  [{:name "Billy" :age 35}
   {:name "Joey" :age 32}
   {:name "Franky" :age 25}])

(defn rf [acc {:keys [name age]}]
  (if (>= age 30)
    (conj acc name)
    acc))

(reduce rf [] users)

; comp
(map (comp keyword
           string/lower-case
           second)
     [[2053 "Billy"]
      [1234 "Franky"]
      [9833 "Joey"]])

; partial
(map (partial + 100) [1 2 3 4 5])
(map (partial filter even?)
     [[0 1 2 3 5]
      [2 5 8 9]])

; Multiple fn arities
(defn multi-fn
  ([x] x)
  ([x y] y)
  ([x y z] z))

(multi-fn 1)
(multi-fn 1 2)
(multi-fn 1 2 3)

(defn new-user
  ([name]
   (new-user name :pending))
  ([name status]
   {:name name
    :status status}))

(new-user "Billy")
(new-user "Billy" :active)

; named anonymous fn
(defn anon-test []
  (let [f
        (fn f [x]
          (if (< x 5)
            (f (inc x))
            x))]
    (f 1)))

(anon-test)

; loop/recur
(defn recur-test [x]
  (if (< x 5)
    (recur (inc x))
    x))

(recur-test 1)
(recur-test 10)

(defn loop-recur-test [y]
  (loop [x 1]
    (let [z (+ x y)]
      (if (< z 10)
        (recur (inc x))
        z))))

(loop-recur-test 1)
(loop-recur-test 12)
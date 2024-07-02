(ns aoc.one
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn handle-input
  []
  (line-seq (io/reader (io/file "inputs/one.txt"))))

(defn func
  []
  (let [input (handle-input)]
    (->> input 
         (map #(re-seq #"[0-9]" %)) 
         (map (juxt first last))
         (map str/join)
         (map parse-long)
         (reduce +))))

(defn get-digits
  [input]
  (->> 
   (take (count input) (iterate #(subs % 1) input)) 
   (map #(re-find #"^(zero|one|two|three|four|five|six|seven|eight|nine|[0-9])" %)) 
   (map first)
   (filter some?)))

(defn func-two
  []
  (let [input (handle-input)
        letter-to-number {"zero" 0 "one" 1 "two" 2 "three" 3 "four" 4 "five" 5 "six" 6 "seven" 7 "eight" 8 "nine" 9 "0" 0 "1" 1 "2" 2 "3" 3 "4" 4 "5" 5 "6" 6 "7" 7 "8" 8 "9" 9}]
    (->> input
         (map get-digits)
         (map (fn [x] (map letter-to-number x))) 
         (map (juxt first last))
         (map str/join)
         (map parse-long)
         (reduce +))))

(defn -main
  []
  (println "The answer for the first part is: " (func))
  (println "The answer for the second part is: " (func-two)))
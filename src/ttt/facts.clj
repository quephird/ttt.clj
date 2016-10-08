(ns ttt.facts
  (:require [clara.rules :refer :all]))

(defrecord Square [x y occupied-by])
(defrecord Move [x y player])

(defn make-new-board []
  (for [x (range 3) y (range 3)]
    (->Square x y :nobody)))

(defn make-new-move [x y player]
  (->Move x y player))

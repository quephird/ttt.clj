(ns ttt.facts
  (:require [clara.rules :refer :all]))

(defrecord Square [x y occupied-by])

(defn make-new-board []
  (for [x (range 3) y (range 3)]
    (->Square x y :nobody)))

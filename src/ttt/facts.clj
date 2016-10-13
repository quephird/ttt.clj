(ns ttt.facts
  (:require [clara.rules :refer :all]))

(defrecord Square [c r occupied-by])
(defrecord MouseClick [x y player])
(defrecord Move [c r player])
(defrecord InvalidMove [])

(defn make-new-board []
  (for [c (range 3) r (range 3)]
    (->Square c r :nobody)))

(defn make-mouse-click [x y player]
  (->MouseClick x y player))

(defn make-new-valid-move [c r player]
  (->ValidMove c r player))

(ns ttt.graphics
  (:require [clara.rules :as rules]
            [quil.core :as quil]
            [ttt.queries :as queries]))

(defn nought [x y]
  (quil/push-matrix)
  (quil/stroke 200 0 0)
  (quil/translate (+ 200 (* x 200)) (+ 200 (* y 200)))
  (quil/ellipse 0 0 125 125)
  (quil/pop-matrix))

(defn cross [x y]
  (quil/push-matrix)
  (quil/stroke 0 0 0)
  (quil/translate (+ 200 (* x 200)) (+ 200 (* y 200)))
  (quil/line -63 -63 63 63)
  (quil/line -63 63 63 -63)
  (quil/pop-matrix))

(defn cell [state x y]
  (let [{{:keys [x y occupied-by]} :?square} (queries/select-square state x y)]
    (case occupied-by
      :nought (nought x y)
      :cross (cross x y)
      nil)))

(defn board [state]
  (let [w  (quil/width)
        h  (quil/height)]
    (quil/stroke 255)
    (quil/line 300 100 300 700)
    (quil/line 500 100 500 700)
    (quil/line 100 300 700 300)
    (quil/line 100 500 700 500)
    (doseq [x (range 3) y (range 3)]
      (cell state x y))))

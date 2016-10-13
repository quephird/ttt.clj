(ns ttt.graphics
  (:require [clara.rules :as rules]
            [quil.core :as quil]
            [ttt.queries :as queries]))

(defn nought [c r]
  (quil/push-matrix)
  (quil/stroke 200 0 0)
  (quil/translate (+ 200 (* c 200)) (+ 200 (* r 200)))
  (quil/ellipse 0 0 125 125)
  (quil/pop-matrix))

(defn cross [c r]
  (quil/push-matrix)
  (quil/stroke 0 0 0)
  (quil/translate (+ 200 (* c 200)) (+ 200 (* r 200)))
  (quil/line -63 -63 63 63)
  (quil/line -63 63 63 -63)
  (quil/pop-matrix))

(defn cell [state c r]
  (let [{{:keys [c r occupied-by]} :?square} (queries/select-square state c r)]
    (case occupied-by
      :nought (nought c r)
      :cross (cross c r)
      nil)))

(defn board [state]
  (let [w  (quil/width)
        h  (quil/height)]
    (quil/stroke 255)
    (quil/line 300 100 300 700)
    (quil/line 500 100 500 700)
    (quil/line 100 300 700 300)
    (quil/line 100 500 700 500)
    (doseq [c (range 3) r (range 3)]
      (cell state c r))))

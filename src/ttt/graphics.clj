(ns ttt.graphics
  (:require [quil.core :as quil]))

(defn nought [x y]
  (quil/push-matrix)
  (quil/stroke 200 0 0)
  (quil/translate (+ 200 (* x 200)) (+ 200 (* y 200)))
  (quil/ellipse 0 0 125 125)
  (quil/pop-matrix))

(defn board [state]
  (let [w  (quil/width)
        h  (quil/height)]
    (quil/color 255)
    (quil/line 300 100 300 700)
    (quil/line 500 100 500 700)
    (quil/line 100 300 700 300)
    (quil/line 100 500 700 500)
    ; (println (first (rules/query state find-square)))
    (doseq [x (range 3) y (range 3)]
      (nought x y))))

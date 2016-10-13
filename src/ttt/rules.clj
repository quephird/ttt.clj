(ns ttt.rules
  (:require [clara.rules :refer :all]
            [clara.rules.accumulators :as acc]
            [ttt.facts :as facts])
  (:import  (ttt.facts MouseClick Move ValidMove Square)))

(defrule validate-mouse-click
  [?click <- MouseClick
    (= ?x x)
    (= ?y y)
    (= ?player player)]
  [:test (and (> ?x 100) (< ?x 700) (> ?y 100) (< ?y 700))]
  =>
  (insert-unconditional!
    (facts/->Move
      (int (/ (- ?x 100) 200.0))
      (int (/ (- ?y 100) 200.0))
      ?player))
  (retract! ?click))

(defrule detect-invalid-move
  [?move <- Move
    (= ?c c)
    (= ?r r)
    (= ?player player)]
  [?square <- Square
    (= ?c c)
    (= ?r r)
    (not= :nobody occupied-by)]
  =>
  (println "Invalid move!!!")
  (insert-unconditional! (facts/->InvalidMove))
  (retract! ?move))

(defrule apply-valid-move
  [?move <- Move
    (= ?c c)
    (= ?r r)
    (= ?player player)]
  [?square <- Square
    (= ?c c)
    (= ?r r)
    (= :nobody occupied-by)]
  =>
  (insert-unconditional! (facts/->Square ?c ?r ?player))
  (retract! ?square)
  (retract! ?move))

; (defrule three-in-a-row?
;     [Square (= ?x1 x)(= ?y1 y)(= ?o1 occupied-by)]
;     [Square (= ?x2 x)(= ?y2 y)(= ?o2 occupied-by)]
;     [Square (= ?x3 x)(= ?y3 y)(= ?o3 occupied-by)]
;     [:test (and (= ?x1 ?x2 ?x3)
;                 (not= ?y1 ?y2 ?y3)
;                 (< ?y1 ?y2 ?y3)
;                 (not= ?o1 :nobody)
;                 (= ?o1 ?o2 ?o3))]
;   =>
;   (println "We have a winner?"))

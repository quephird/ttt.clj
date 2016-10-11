(ns ttt.rules
  (:require [clara.rules :refer :all]
            [clara.rules.accumulators :as acc]
            [ttt.facts :as facts])
  (:import  (ttt.facts Move Square)))

(defrule apply-move
  [?move <- Move
    (= ?x x)
    (= ?y y)
    (= ?player player)]
  [?square <- Square
    (= ?x x)
    (= ?y y)
    (= :nobody occupied-by)]
  =>
  (insert-unconditional! (facts/->Square ?x ?y ?player))
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

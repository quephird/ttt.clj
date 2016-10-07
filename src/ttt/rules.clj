(ns ttt.rules
  (:require [clara.rules :refer :all]
            [clara.rules.accumulators :as acc]
            [clara.tools.inspect :refer :all]))

(defrecord Move [x y player])

(defrecord Square [x y occupied-by])

(defrule update-square
  [?move <- Move
    (= ?x x)
    (= ?y y)
    (= ?player player)]
  [?square <- Square
    (= ?x x)
    (= ?y y)
    (= :nobody occupied-by)]
  =>
  (println "Square updated!")
  (insert-unconditional! (->Square ?x ?y ?player))
  (retract! ?square)
  (retract! ?move))

(defrule three-in-a-row?
    [Square (= ?x1 x)(= ?y1 y)(= ?o1 occupied-by)]
    [Square (= ?x2 x)(= ?y2 y)(= ?o2 occupied-by)]
    [Square (= ?x3 x)(= ?y3 y)(= ?o3 occupied-by)]
    [:test (and (= ?x1 ?x2 ?x3)
                (not= ?y1 ?y2 ?y3)
                (< ?y1 ?y2 ?y3)
                (not= ?o1 :nobody)
                (= ?o1 ?o2 ?o3))]
  =>
  (println "We have a winner?")
  )

(defquery show-moves[]
  [?move <- Move])

(defquery show-board[]
  [?square <- Square])

(defn first-move []
  (let [new-session (-> (mk-session 'ttt.core)
                        (insert (->Move 0 0 :danielle)
                                (->Square 0 0 :nobody)
                                (->Square 0 1 :danielle)
                                (->Square 0 2 :danielle)
                                (->Square 1 0 :danielle)
                                (->Square 1 1 :nobody)
                                (->Square 1 2 :nobody)
                                (->Square 2 0 :danielle)
                                (->Square 2 1 :nobody)
                                (->Square 2 2 :nobody))
                        (fire-rules))]
    (println (count (query new-session show-board)))
    (println (query new-session show-moves))
    ))

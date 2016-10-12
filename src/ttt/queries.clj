(ns ttt.queries
  (:require [clara.rules :refer :all]
            [ttt.facts])
  (:import (ttt.facts Square)))

(defquery find-square [:?x :?y]
  [?square <- Square (= ?x x) (= ?y y)])

(defn select-square [state x y]
  (first (query state find-square :?x x :?y y)))

(defn is-square-free? [state x y]
  (-> (query state find-square :?x x :?y y)
    first
    (get-in [:?square :occupied-by])
    (= :nobody)))

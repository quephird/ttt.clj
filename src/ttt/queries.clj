(ns ttt.queries
  (:require [clara.rules :refer :all]
            [ttt.facts])
  (:import (ttt.facts Square)))

(defquery find-square [:?c :?r]
  [?square <- Square (= ?c c) (= ?r r)])

(defn select-square [state c r]
  (first (query state find-square :?c c :?r r)))

(defn is-square-free? [state c r]
  (-> (query state find-square :?c c :?r r)
    first
    (get-in [:?square :occupied-by])
    (= :nobody)))

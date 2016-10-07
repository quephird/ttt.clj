(ns ttt.queries
  (:require [clara.rules :refer :all]
            [ttt.facts])
  (:import (ttt.facts Square)))

(defquery find-square []
  [?square <- Square])

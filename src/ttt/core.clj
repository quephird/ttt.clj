(ns ttt.core
  (:require [clara.rules :as rules]
            [quil.core :as quil :include-macros true]
            [quil.middleware :as m]
            [ttt.facts :as facts]
            [ttt.graphics :as graphics]
            [ttt.rules]))

;; Start game with blank board
(defn setup []
  (quil/stroke-weight 10)
  (quil/ellipse-mode :center)
  (quil/no-fill)
  (let [new-session (rules/mk-session 'ttt.facts 'ttt.queries 'ttt.rules)
        new-board   (facts/make-new-board)
        session-with-board (apply rules/insert new-session new-board)]
    session-with-board))

;; Update game state
(defn update [state]
  state)

;; Get user input
(defn mouse-clicked [state event]
  ;; TODO: Eventually need to check if it's the computer's turn
  (let [x (quil/mouse-x)
        y (quil/mouse-y)]
    (if (and (> x 100) (< x 700) (> y 100) (< y 700))
      (let [c (int (/ (- x 100) 200.0))
            r (int (/ (- y 100) 200.0))
            new-move (facts/make-new-move c r :nought)
            new-state (-> state
                        (rules/insert new-move)
                        (rules/fire-rules))]
        new-state)
        state)))

;; Draw current board
(defn draw [state]
  (graphics/board state))

(quil/sketch
  :title         "tic tac toe with quil and clara"
  :size          [800 800]
  :setup         setup
  :update        update
  :draw          draw
  :mouse-clicked mouse-clicked
  :middleware    [m/fun-mode])

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
  (let [new-session (rules/mk-session 'ttt.facts 'ttt.graphics 'ttt.rules)
        new-board   (facts/make-new-board)
        session-with-board (apply rules/insert new-session new-board)]
    session-with-board))

;; Wait for user input

;; Update game state
(defn update [state]
  state)

(defn mouse-clicked [state event]
  (let [new-move (facts/make-new-move 0 2 :nought)
        new-state (-> state
                    (rules/insert new-move)
                    (rules/fire-rules))]
    new-state))

;; Draw current board
(defn draw [state]
  ;; This is not a permanent strategy; it is just to test that new Move
  ;; facts can be added to the session, the rules can be fired, and the
  ;; board updated accordingly.
  (graphics/board state))

;; Mouse handlers

(quil/sketch
  :title         "tic tac toe with quil and clara"
  :size          [800 800]
  :setup         setup
  :update        update
  :draw          draw
  :mouse-clicked mouse-clicked
  :middleware    [m/fun-mode]
  )

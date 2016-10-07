(ns ttt.core
  (:require [clara.rules :as rules]
            [quil.core :as quil :include-macros true]
            [quil.middleware :as m]
            [ttt.facts :as facts]
            [ttt.graphics :as graphics]))

;; Start game with blank board
(defn setup []
  (quil/stroke-weight 10)
  (quil/ellipse-mode :center)
  (quil/no-fill)
  (quil/no-loop)
  (let [new-session (rules/mk-session 'ttt.facts 'ttt.graphics)
        new-board   (facts/make-new-board)
        session-with-board (apply rules/insert new-session new-board)]
    session-with-board))

;; Wait for user input

;; Update game state
(defn update [state]
  state)

;; Draw current board
(defn draw [state]
  (graphics/board state))

;; Mouse handlers

(quil/sketch
  :title      "tic tac toe"
  :size       [800 800]
  :setup      setup
  :update     update
  :draw       draw
  :middleware [m/fun-mode]
  )

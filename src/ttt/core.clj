(ns ttt.core
  (:require [clara.rules :as rules]
            [quil.core :as quil :include-macros true]
            [quil.middleware :as m]
            [ttt.facts :as facts]
            [ttt.graphics :as graphics]
            [ttt.queries :as queries]
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
  ;; TODO: Need to move mouse handler into separate namespace
  (let [x (quil/mouse-x)
        y (quil/mouse-y)
        new-move (facts/make-mouse-click x y :cross)
        new-state (-> state
                    (rules/insert new-move)
                    (rules/fire-rules))]
    new-state))

;; Draw current board
(defn draw [state]
  ;; TODO: Need to rename this method and "render" sounds here
  (graphics/board state))

(quil/sketch
  :title         "tic tac toe with quil and clara"
  :size          [800 800]
  :setup         setup
  :update        update
  :draw          draw
  :mouse-clicked mouse-clicked
  :middleware    [m/fun-mode])

(ns kata7.core
  (:use [clojure.set :only (select union)]))

(defn neighbours-of [[y x]]
  (set (for [dy [-1 0 1]
             dx [-1 0 1] :when (not= 0 dy dx)]
         [(+ y dy) (+ x dx)])))

(defn count-living-neighbours-of [world cell]
  (count (select world (neighbours-of cell))))

(defn survives? [world cell]
  (let [living-neighbours (count-living-neighbours-of world cell)]
    (and (world cell) (> living-neighbours 1) (< living-neighbours 4))))

(defn revives? [world cell]
  (= 3 (count-living-neighbours-of world cell)))

(defn candidates [world]
  (union world (apply union (map #(neighbours-of %) world)))) 

(defn in-world? [height width [y x]]
  (not (or (neg? y) (neg? x) (>= y height) (>= x width))))

(defn evolve [{:keys [grid width height] :as world}]
  (let [surviving-cells #(some true? [(survives? grid %)
                                      (revives? grid %)])
        cells-in-grid #(not-any? false? [(in-world? height width %)])]
    (assoc world :grid
           (->> (candidates grid)
                (select cells-in-grid)
                (select surviving-cells)))))

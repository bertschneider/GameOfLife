(ns kata6.core
  "Game of Life - Kata 6
   Heavily inspired by Uncle Bob's solution
   http://blog.objectmentor.com/articles/2010/08/15/game-of-life-with-lunivore")

(defn N [[y x]] [(dec y) x])
(defn S [[y x]] [(inc y) x])
(defn W [[y x]] [y (dec x)])
(defn E [[y x]] [y (inc x)])

(defn NW [cell] (-> cell N W))
(defn NE [cell] (-> cell N E))
(defn SW [cell] (-> cell S W))
(defn SE [cell] (-> cell S E))

(def directions [NW N NE W E SW S SE])

(defn surrounding-of [cell]
  (set (map #(% cell) directions)))

(defn neighbours-of [world cell]
  (set (filter #(contains? world %) (surrounding-of cell))))

(defn neighbours-count [world cell]
  (count (neighbours-of world cell)))

(defn survives? [world cell]
  (let [neighbours (neighbours-count world cell)]
    (or (and (> neighbours 1) (< neighbours 4) (contains? world cell))
        (= 3 neighbours))))

(defn candidates [world]
  (->> (map #(cons % (surrounding-of %)) world)
       (apply concat)
       (set)))

(defn evolve [world]
  (set (filter #(survives? world %) (candidates world))))

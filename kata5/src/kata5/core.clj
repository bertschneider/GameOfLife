(ns kata5.core
  "Game of Life - Kata 5
   Heavily inspired by Uncle Bob's solution
   http://blog.objectmentor.com/articles/2010/08/15/game-of-life-with-lunivore")

(defn neighbours-of [[y x]]
  (for [dy [-1 0 1]
        dx [-1 0 1] :when (not= 0 dy dx)]
    [(+ y dy) (+ x dx)]))

(defn count-neighbours [world cell]
  (->> (neighbours-of cell)
       (filter #(contains? world %))
       (count))) 

(defn candidates [world]
  (->> (map #(cons % (neighbours-of %)) world)
       (apply concat)
       (set)))

(defn survives? [world cell]
  (let [neighbours (count-neighbours world cell)]
    (or (and (> neighbours 1) (< neighbours 4) (contains? world cell))
        (and (= neighbours 3)))))

(defn evolve [world]
  (set (filter #(survives? world %) (candidates world))))

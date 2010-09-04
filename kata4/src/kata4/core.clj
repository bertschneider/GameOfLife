(ns kata4.core)

(defn cell-at [{:keys [grid width height]} x y]
  (if (or (neg? x) (neg? y) (>= x width) (>= y height))
    nil
    (nth grid (+ x (* y width)) nil)))

(defn neighbours [world x y]
  (filter (complement nil?)
          (for [dy [-1 0 1]
                dx [-1 0 1] :when (not= 0 dx dy)]
            (cell-at world (+ x dx) (+ y dy)))))

(defn alive-neighbours [world x y]
  (reduce #(+ %1 %2) 0 (neighbours world x y)))

(defn alive-grid [{:keys [grid width height] :as world}]
  (for [y (range 0 height)
        x (range 0 width)]
    {:value  (cell-at world x y)
     :alives (alive-neighbours world x y)}))

(defn evolve-cell [{:keys [value alives]}]
  (if (or (and (= value 1) (or (= alives 2) (= alives 3)))
          (and (= value 0) (= alives 3)))
    1
    0))

(defn evolve [world]
  (assoc world :grid (map evolve-cell (alive-grid world))))


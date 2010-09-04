(ns kata4.core-test
  (:use [kata4.core] :reload-all)
  (:use [clojure.test]))

(deftest test-cell-at
  (let [world {:width 2 :height 2 :grid [1 2
                                         3 4]}]
    (are [v x y] (= v (cell-at world x y))
          1 0 0
          2 1 0
          3 0 1
          4 1 1
          nil -1 -1
          nil -1  1
          nil  2  0)))

(deftest test-neighbours
  (let [world {:width 3 :height 3 :grid [1 2 3
                                         4 5 6
                                         7 8 9]}]
    (are [v x y] (= v (neighbours world x y))
         [1 2 3 4 6 7 8 9] 1 1
         [2 4 5] 0 0
         [5 6 8] 2 2)))

(deftest test-alive-neighbours
  (let [world {:width 3 :height 3 :grid [0 0 0
                                         0 0 1
                                         1 1 0]}]
    (are [v x y] (= v (alive-neighbours world x y))
          0 0 0
          1 1 0
          2 0 1
          3 1 1)))

(deftest test-alive-grid
  (let [world {:width 3 :height 3 :grid [0 1 0
                                         1 0 1
                                         0 0 0]}]
    (is (= [2 2 2 1 3 1 1 2 1] (map :alives (alive-grid world))))))

(deftest test-evolve-cell
  (is (= 0 (evolve-cell {:value 1 :alives 1})))
  (is (= 0 (evolve-cell {:value 1 :alives 4})))
  (is (= 1 (evolve-cell {:value 1 :alives 2})))
  (is (= 1 (evolve-cell {:value 1 :alives 3})))
  (is (= 1 (evolve-cell {:value 0 :alives 3})))
  (is (= 0 (evolve-cell {:value 0 :alives 2}))))

(deftest test-evolve
  (let [world {:width 8 :height 4 :grid [0 0 0 0 0 0 0 0
                                         0 0 0 0 1 0 0 0
                                         0 0 0 1 1 0 0 0
                                         0 0 0 0 0 0 0 0]}]
    (is (= (evolve world) (assoc world :grid [0 0 0 0 0 0 0 0
                                              0 0 0 1 1 0 0 0
                                              0 0 0 1 1 0 0 0
                                              0 0 0 0 0 0 0 0])))))

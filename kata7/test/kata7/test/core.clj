(ns kata7.test.core
  (:use [kata7.core])
  (:use [lazytest.describe :only (describe it)])
  (:use [lazytest.random]))

(describe neighbours-of
  (it "should return the neighbours of cell [2 2]"
    (= #{[1 1] [1 2] [1 3] [2 1] [2 3] [3 1] [3 2] [3 3]}
       (neighbours-of [2 2]))))

(describe count-living-neighbours-of
  (it "should count no living neighbours in a world without living cells"
    (= 0 (count-living-neighbours-of #{} [1 1])))
  (it "should count one if the given cell has one living neighbour"
    (= 1 (count-living-neighbours-of #{[1 1]} [2 2])))
  (it "should count eight if all neighbours of the given cell are alive"
    (= 8 (count-living-neighbours-of #{[1 1] [1 2] [1 3] [2 1] [2 3] [3 1] [3 2] [3 3]} [2 2]))))

(describe survives?
  (it "should return false if a living cell dies of starvation"
    (not (survives? #{[1 1] [1 2]} [1 2])))
  (it "should return false if a living cell dies of overpopulation"
    (not (survives? #{[1 1] [1 2] [1 3] [2 1] [2 2]} [2 2])))
  (it "should return true if a living cell has three living neighbours"
    (survives? #{[1 1] [1 2] [1 3] [2 2]} [2 2]))
  (it "should return true if a living cell has two living neighbours"
    (survives? #{[1 1] [1 2] [1 3]} [1 2])))

(describe revives?
  (it "should return true if a dead cell has exactly three living neighbours"
    (revives? #{[1 1] [1 2] [1 3]} [2 2]))
  (it "should return false if a dead cell has two living neighbours"
    (not (revives? #{[1 1] [1 3]} [1 2])))
  (it "should return false if a dead cell has four living neighbours"
    (not (revives? #{[1 1] [1 2] [1 3] [2 1]} [2 2]))))

(describe candidates
  (it "should return a union of all living cells in the world and their neighbours"
    (= #{[1 1] [1 2] [1 3] [2 1] [2 2] [2 3] [3 1] [3 2] [3 3]}
       (candidates #{[2 2]}))
    (= #{[1 1] [1 2] [1 3] [2 1] [2 2] [2 3] [3 1] [3 2] [3 3] [4 1] [4 2] [4 3] [4 4]}
       (candidates #{[2 2] [3 2]}))))

(describe in-world?
  (it "should return false if the given cell has negative coordinates"
    (not (in-world? 3 3 [-1 -1])))
  (it "should return false if the given cell has a greater or equal x coordinate as the given width param"
    (not (in-world? 3 3 [3 3])))
  (it "should return false if the given cell has a greater or equal y coordinate as the given height param"
    (not (in-world? 3 3 [3 3])))
  (it "should return true if the given cell is inside the stated boundaries"
    (in-world? 1 1 [0 0])))

(describe evolve
  (it "should evolve the Game Of Life world of the exercise to the next state"
    (= {:width 8 :height 6 :grid #{[3 4] [3 5] [4 4] [4 5]}}
       (evolve {:width 8 :height 6 :grid #{[3 5] [4 4] [4 5]}})))
  (it "should only evolve cells in the world"
    (= {:width 8 :height 6 :grid #{[0 2] [1 2]}}
       (evolve {:width 8 :height 6 :grid #{[0 1] [0 2] [0 3]}}))))










(ns kata5.test.core
  (:use [kata5.core] :reload)
  (:use [clojure.test]))

(deftest test-neighbours-of
  (is (= (neighbours-of [2 2])
         [[1 1] [1 2] [1 3]
          [2 1]       [2 3]
          [3 1] [3 2] [3 3]])))

(deftest test-count-neighbours
  (is (= 3 (count-neighbours #{[1 1] [1 2] [1 3]} [2 2]))))

(deftest test-candidates
  (is (= (candidates #{[1 5] [2 4]})
         #{[0 4] [0 5] [0 6] [1 4] [1 5] [1 6] [2 4] [2 5] [2 6]
           [1 3] [2 3] [3 3] [3 4] [3 5]})))

(deftest test-survives?
  (is (survives? #{[1 0] [1 1] [1 2]} [1 1]))
  (is (survives? #{[1 0] [1 1] [1 2] [2 2]} [2 2]))
  (is (not (survives? #{[1 2] [2 2]} [2 2])))
  (is (not (survives? #{[1 2] [1 3] [1 4] [2 2] [2 3]} [1 3])))
  (is (survives? #{[1 1] [1 2] [1 3]} [2 2])))

(deftest test-evolve
  (is (= (evolve #{[2 5] [3 4] [3 5]})
         #{[2 4] [2 5] [3 4] [3 5]})))


(ns kata6.test.core
  (:use [kata6.core] :reload)
  (:use [clojure.test]))

(deftest test-surrounding-of
  (is (= (surrounding-of [1 1])
         #{[0 0] [0 1] [0 2] [1 0] [1 2] [2 0] [2 1] [2 2]})))

(deftest test-neighbours-of
  (is (= (neighbours-of #{[1 1] [1 2] [1 3] [1 4]} [2 2])
         #{[1 1] [1 2] [1 3]})))

(deftest test-neighbours-count
  (is (= (neighbours-count #{[1 1] [1 2] [1 3] [1 4]} [2 2])
         3)))

(deftest test-survives?
  (is (survives? #{[1 1] [1 2] [1 3]} [1 2]))
  (is (survives? #{[1 1] [1 2] [1 3] [2 2]} [2 2]))
  (is (survives? #{[1 1] [1 2] [1 3]} [2 2])))

(deftest test-candidates
  (is (= (candidates #{[2 2] [3 2]})
         #{[1 1] [1 2] [1 3]
           [2 1] [2 2] [2 3]
           [3 1] [3 2] [3 3]
           [4 1] [4 2] [4 3]})))

(deftest test-evolve
  (is (= (evolve #{[2 5] [3 4] [3 5]})
         #{[2 4] [2 5] [3 4] [3 5]})))

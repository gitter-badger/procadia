(ns hard.physics
  (:use [hard.core])
  (:import
    [UnityEngine Physics]))

(defn gravity [] (Physics/gravity))

(defn gravity! [v3] (set! (Physics/gravity) (->v3 v3)))

(defn rigidbody? [o] (instance? UnityEngine.Rigidbody o))
(defn ->rigidbody [v]
  (if-let [o (->go v)] (component o UnityEngine.Rigidbody) nil))

(defn ->rigidbody2d [v]
  (if-let [o (->go v)] (component o UnityEngine.Rigidbody2D) nil))

(defn force! [body x y z] (.AddRelativeForce body x y z))
(defn torque! [body x y z] (.AddRelativeTorque body x y z))
(defn kinematic! [go v] (set! (.isKinematic (->rigidbody go)) v))

(defn ->velocity [o]
  (if-let [body (cond (rigidbody? o) o (gameobject? o) (->rigidbody o))]
    (.velocity body)))
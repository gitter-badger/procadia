(ns hard.edit
	(:use [hard.core])
	(:require hard.life)
	(:import 
		     [UnityEngine Gizmos]))
 
(defn sel [] (Selection/objects))

 (defn sel! 
 	([v] (cond (gameobject? v) (set! (Selection/objects) (into-array [v]))
 			   (sequential? v) (set! (Selection/objects) (into-array v))))
 	([v & more] (set! (Selection/objects) (into-array (cons v more)))))

(defn clear-flags! [go]
	(import '[UnityEngine HideFlags])
	(set! (.hideFlags go) HideFlags/None))


(defn active [] (Selection/activeGameObject))

(defn no-edit! [go]
	(import '[UnityEngine HideFlags])
	(set! (.hideFlags go) HideFlags/NotEditable))

(defn hide! [go]
	(import '[UnityEngine HideFlags])
	(set! (.hideFlags go) HideFlags/HideInHierarchy))

(defn scene-use [-ns & more]
	(let [sn (str -ns)
		  nombre (str "(use " -ns ")")
		  go (or (find-name nombre) (GameObject. nombre))
		  hook (do (destroy! (.GetComponent go hard.life.Use))
		  		   (.AddComponent go hard.life.Use))]
		(! hook ns sn)
		(no-edit! go)))     
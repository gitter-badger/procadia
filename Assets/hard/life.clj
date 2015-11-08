(ns hard.life
  (:use hard.core hard.pdf)
  (:require arcadia.core))
    
  
  
(defpdf awake)
(defpdf start)
(defpdf destroy)
(defpdf mouse-down)
(defpdf mouse-up)
(defpdf mouse-enter)
(defpdf mouse-over)
(defpdf mouse-exit)
(defpdf mouse-drag)
(defpdf collision-enter)
(defpdf collision-exit)
(defpdf collision-stay)
(defpdf collision-enter-2d)
(defpdf collision-exit-2d)
(defpdf collision-stay-2d)
(defpdf trigger-enter)
(defpdf trigger-exit)
(defpdf trigger-stay)
(defpdf trigger-enter-2d)
(defpdf trigger-exit-2d)
(defpdf trigger-stay-2d)
 

(arcadia.core/defcomponent Handler [^System.String tag ^System.String id]
  (Awake [this] (awake this tag id))
  (Start [this] (start this tag id))
  (OnDestroy [this] (destroy this tag id))
  (OnMouseDown [this] (mouse-down this tag id))
  (OnMouseEnter [this] (mouse-enter this tag id))
  (OnMouseExit [this] (mouse-exit this tag id))
  ;(OnMouseOver [this] (mouseover this tag id))
  (OnMouseUp [this] (mouse-up this tag id))
  (OnMouseDrag [this] (mouse-drag this tag id))
  
  (OnCollisionEnter [this other] (collision-enter this other tag id))
  (OnCollisionExit [this collider] (collision-exit this collider tag id))
  (OnCollisionEnter2D [this collider] (collision-enter-2d this collider tag id))
  (OnCollisionExit2D [this collider] (collision-exit-2d this collider tag id))

  ; (OnCollisionStay [this collider] (collision-stay this collider tag id))
  ; (OnCollisionStay2D [this collider] (collision-stay-2d this collider tag id))

  (OnTriggerEnter [this collider] (trigger-enter this collider tag id))
  (OnTriggerExit [this collider] (trigger-exit this collider tag id))
  (OnTriggerEnter2D [this collider] (trigger-enter-2d this collider tag id))
  (OnTriggerExit2D [this collider] (trigger-exit-2d this collider tag id))

  ; (OnTriggerStay [this collider] (trigger-stay this collider tag id))
  ; (OnTriggerStay2D [this collider] (trigger-stay-2d this collider tag id))
  )
  
    
(arcadia.core/defcomponent Use [^String ns]
  (Awake [this] 
    (use (symbol ns) 'pdf.core)
    (awake this ns))
  (Start [this] (start this ns))
  (Update [this] (do-deferred)))

(arcadia.core/defcomponent Update [f]
 (Update [this] ((.f this) this)))

(defn route-update [go f]
  (let [c (.AddComponent go Update)]
    (set! (.f c) f)))
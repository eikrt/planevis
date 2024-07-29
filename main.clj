(ns myswingproject.core
  (:import
   [javax.swing SwingUtilities JFrame JLabel JButton JPanel Timer JMenu JOptionPane]
   [java.awt Canvas Color]
   [java.awt.event ActionListener]
   ))
(def WIN-W 600)
(def WIN-H 400)
(defn paint [graphics]
(.setColor graphics Color/RED)
(.fillOval graphics (- 32 32 ) (- 32 32) (* 32 32 ) (* 32 32)))

(defn draw-panel []
  (proxy [JPanel] []
    (paintComponent [graphics]
      (proxy-super paintComponent graphics)
    (paint graphics))))

(defn show
  []
  (let [main-frame (doto (JFrame. "PlaneVis")
                     (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE))
        dp (draw-panel)
        repaint-action (proxy [ActionListener] []
                         (actionPerformed [_]
                           (.repaint dp)))
        repaint-timer (Timer. 1000 repaint-action)
        content-pane (.getContentPane  main-frame)
        canvas (Canvas.)
        repaint-action (proxy [ActionListener] []
                         ( actionPerformed[_]
                          (.repaint canvas)))
        ]
    (.add content-pane canvas)
    (.pack main-frame)
    (.add main-frame dp)
    (.setSize main-frame WIN-W WIN-H)
    (.start repaint-timer)
    (.setVisible main-frame true)))
(defn gui []
  (SwingUtilities/invokeLater show))
(gui)

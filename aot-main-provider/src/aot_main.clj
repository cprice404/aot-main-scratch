(ns aot-main
  ;; This is necessary to cause this class to be aot-compiled into
  ;; java byte code.
  (:gen-class)
  ;; unfortunately, it will also cause everything (transitively) referenced
  ;; here to be compiled to java byte code as well.
  (:require [real-main :as real]))

(defn -main
  [& args]
  (apply real/real-main args))

(ns aot-main
  ;; This is necessary to cause this class to be aot-compiled into
  ;; java byte code.
  (:gen-class)

  ;; So, to get around this, we need to get rid of the require.

;  ;; unfortunately, it will also cause everything (transitively) referenced
;  ;; here to be compiled to java byte code as well.
;  (:require [real-main :as real])
  )

(defn -main
  [& args]
  ;; and now we have to access the function dynamically.
  (require 'real-main)
  (apply (resolve 'real-main/real-main) args))

(ns script
  (:require [babashka.pods :as pods]))

(prn (pods/load-pod "./pod-babashka-fswatcher"))

(require '[pod.babashka.fswatcher :as fw])

(def watcher 
  (fw/watch 
    (first *command-line-args*)
    (fn [event] (println event))
    {:delay-ms 250 :recursive true}))

(.join (Thread/currentThread))

(comment
  (fw/unwatch watcher))



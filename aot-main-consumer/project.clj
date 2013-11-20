(defproject cprice404/aot-main-consumer "0.1.0-SNAPSHOT"
  :description "A library that uses an aot-main from a dependency"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 ;; Logging
                 [org.clojure/tools.logging "0.2.6"]
                 ;; aot-main provider
                 [cprice404/aot-main-provider "0.1.0-SNAPSHOT"]]

  :profiles {:dev {:resource-paths ["test-resources"]}}

  :deploy-repositories [["releases" "http://nexus.delivery.puppetlabs.net/content/repositories/releases/"]
                        ["snapshots" "http://nexus.delivery.puppetlabs.net/content/repositories/snapshots/"]]

  ;; This is necessary in order for the uberjar to be runnable via
  ;;  `java -jar`
  :main aot-main

  )

(ns tablecloth.time.api
  {:clj-kondo/config '{:linters {:unresolved-symbol {:level :off}}}}
  (:require [tech.v3.datatype.export-symbols :as exporter]
            [tablecloth.time.time-literals :refer [modify-printing-of-time-literals-if-enabled!]]))

(modify-printing-of-time-literals-if-enabled!)

(exporter/export-symbols tablecloth.time.api.slice
                         slice)

(exporter/export-symbols tablecloth.time.api.adjust-frequency
                         adjust-frequency)

(exporter/export-symbols tablecloth.time.api.rolling-window
                         rolling-window)

(exporter/export-symbols tablecloth.time.api.index-by
                         index-by)

(exporter/export-symbols tablecloth.time.api.converters
                         convert-to
                         down-to-nearest
                         ->seconds
                         ->minutes
                         ->hours
                         ->days
                         ->weeks-end
                         ->months-end
                         ->quarters-end
                         ->years-end
                         ->every
                         string->time)

(exporter/export-symbols tablecloth.time.api.rolling-window
                         rolling-window)


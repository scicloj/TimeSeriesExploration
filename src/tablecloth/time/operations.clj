(ns tablecloth.time.operations
  (:import java.util.TreeMap)
  (:require [tablecloth.time.index :refer [get-index-type slice-index]]
            [tablecloth.api :as tablecloth]
            [tech.v3.datatype.errors :as errors]
            [tick.alpha.api :as t]))

(set! *warn-on-reflection* true)

;; TODO Would it be better to do this with a map instead of a mutlimethod?

(defmulti parse-datetime-str
  (fn [datetime-datatype _] datetime-datatype))

(defmethod parse-datetime-str
  java.time.Instant
  [_ date-str]
  (java.time.Instant/parse date-str))

(defmethod parse-datetime-str
  java.time.ZonedDateTime
  [_ date-str]
  (java.time.ZonedDateTime/parse date-str))

(defmethod parse-datetime-str
  java.time.LocalDate
  [_ date-str]
  (java.time.LocalDate/parse date-str))

(defmethod parse-datetime-str
  java.time.LocalDateTime
  [_ date-str]
  (java.time.LocalDateTime/parse date-str))

(defmethod parse-datetime-str
  java.time.YearMonth
  [_ date-str]
  (java.time.YearMonth/parse date-str))

(defmethod parse-datetime-str
  java.time.Year
  [_ date-str]
  (java.time.Year/parse date-str))

(defn slice
  "Returns a subset of dataset's rows as specified by from and to, inclusively.
  From and to are either strings or datetime type literals (e.g. #time/local-date \"1970-01-01\").
  The dataset must have been indexed, and the time unit of the index must match the unit of time
  by which you are attempting to slice.

  Example data:

  |   :A | :B |
  |------|----|
  | 1970 |  0 |
  | 1971 |  1 |
  | 1972 |  2 |
  | 1973 |  3 |

  Example:

  (-> data
      (index-by :A)
      (slice \"1972\" \"1973\"))

  ;; => _unnamed [2 2]:

  |   :A | :B |
  |------|----|
  | 1972 |  2 |
  | 1973 |  3 |
  "
  [dataset from to]
  (let [time-unit (get-index-type dataset)
        from-key (cond
                   (instance? java.time.temporal.Temporal from) from
                   :else (parse-datetime-str time-unit from))
        to-key (cond
                 (instance? java.time.temporal.Temporal to) to
                 :else (parse-datetime-str time-unit to))]
    (cond
      (not= time-unit (class from-key)) (throw (Exception. (format "Time unit of `from` does not match index time unit: %s" time-unit)))
      (not= time-unit (class to-key)) (throw (Exception. (format "Time unit of `to` does not match index time unit: %s" time-unit)))
      :else (slice-index dataset from-key to-key))))


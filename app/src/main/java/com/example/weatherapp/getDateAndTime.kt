package com.example.weatherapp

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

public class getDateAndTime {
    companion object {
        public fun getDate(l: Long): String {
            val forecastDate: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(l),
                TimeZone.getDefault().toZoneId()
            )

            val forecastDateString = forecastDate.toString()
            val forecastDateStringNew = forecastDateString.replace("T", "")
            val forecastDateStringFormat: DateFormat = SimpleDateFormat("yyyy-MM-ddhh:mm:ss")
            val dateToBeParsed: Date = forecastDateStringFormat.parse(forecastDateStringNew)

            val forecastDateStringFormatNew: DateFormat = SimpleDateFormat("MMM dd")

            return forecastDateStringFormatNew.format(dateToBeParsed)
        }

        public fun getTime(l: Long): String {
            var forecastDate: LocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(l),
                TimeZone.getDefault().toZoneId()
            )
            forecastDate = forecastDate.minusHours(5)
            val forecastDateString = forecastDate.toString()
            val forecastDateStringNew = forecastDateString.replace("T", "")
            val forecastDateStringFormat: DateFormat = SimpleDateFormat("yyyy-MM-ddhh:mm:ss")
            val dateToBeParsed: Date = forecastDateStringFormat.parse(forecastDateStringNew)

            val forecastDateStringFormatNew: DateFormat = SimpleDateFormat("hh:mm")

            return forecastDateStringFormatNew.format(dateToBeParsed)
        }
    }
}
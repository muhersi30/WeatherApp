package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import java.text.DateFormat


class ForecastFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager;
    private val forecastItemsList = ArrayList<DayForecast>()
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goBackButton = view.findViewById<Button>(R.id.goBack_button)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        goBackButton.setOnClickListener() {
            findNavController().navigate(R.id.action_forecastFragment_to_currentTempFragment);
        }

        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager

        recyclerAdapter = RecyclerAdapter(forecastItemsList)
        recyclerView.adapter = recyclerAdapter
        prepareForecastItems()
    }

    private fun getDate(l: Long): String {
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

    private fun getTime(l: Long): String {
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

    private fun prepareForecastItems() {

        // All timestamp values obtained from UTC calculator mentioned in the assignment document.

        val day1 : DayForecast = DayForecast(getDate(1664424054000),
            getTime(1664424054000),
            getTime(1664478054000),
            ForecastTemp(30.toFloat(),25.toFloat(),35.toFloat()), 63.toFloat(), 15
        );
        val day2 : DayForecast = DayForecast(getDate(1664510754000),
            getTime(1664510754000),
            getTime(1664564754000),
            ForecastTemp(29.toFloat(),24.toFloat(),30.toFloat()), 64.toFloat(), 14
        );
        val day3 : DayForecast = DayForecast(getDate(1664597454000),
            getTime(1664597454000),
            getTime(1664651454000),
            ForecastTemp(29.toFloat(),23.toFloat(),30.toFloat()), 65.toFloat(), 13
        );
        val day4 : DayForecast = DayForecast(getDate(1664684154000),
            getTime(1664684154000),
            getTime(1664738154000),
            ForecastTemp(29.toFloat(),22.toFloat(),30.toFloat()), 65.toFloat(), 12
        );
        val day5 : DayForecast = DayForecast(getDate(1664770854000),
            getTime(1664770854000),
            getTime(1664824854000),
            ForecastTemp(28.toFloat(),21.toFloat(),30.toFloat()), 67.toFloat(), 11
        );
        val day6 : DayForecast = DayForecast(getDate(1664857554000),
            getTime(1664857554000),
            getTime(1664911554000),
            ForecastTemp(27.toFloat(),20.toFloat(),30.toFloat()), 68.toFloat(), 10
        );
        val day7 : DayForecast = DayForecast(getDate(1664944254000),
            getTime(1664944254000),
            getTime(1664998254000),
            ForecastTemp(26.toFloat(),19.toFloat(),29.toFloat()), 69.toFloat(), 9
        );
        val day8 : DayForecast = DayForecast(getDate(1665030954000),
            getTime(1665030954000),
            getTime(1665084954000),
            ForecastTemp(26.toFloat(),18.toFloat(),28.toFloat()), 70.toFloat(), 8
        );
        val day9 : DayForecast = DayForecast(getDate(1665117654000),
            getTime(1665117654000),
            getTime(1665171654000),
            ForecastTemp(25.toFloat(),17.toFloat(),27.toFloat()), 71.toFloat(), 7
        );
        val day10 : DayForecast = DayForecast(getDate(1665204354000),
            getTime(1665204354000),
            getTime(1665258354000),
            ForecastTemp(24.toFloat(),16.toFloat(),26.toFloat()), 72.toFloat(), 6
        );
        val day11 : DayForecast = DayForecast(getDate(1665291054000),
            getTime(1665291054000),
            getTime(1665345054000),
            ForecastTemp(23.toFloat(),15.toFloat(),26.toFloat()), 73.toFloat(), 5
        );
        val day12 : DayForecast = DayForecast(getDate(1665377754000),
            getTime(1665377754000),
            getTime(1665431754000),
            ForecastTemp(22.toFloat(),14.toFloat(),26.toFloat()), 74.toFloat(), 4
        );
        val day13 : DayForecast = DayForecast(getDate(1665464454000),
            getTime(1665464454000),
            getTime(1665518454000),
            ForecastTemp(21.toFloat(),13.toFloat(),25.toFloat()), 75.toFloat(), 3
        );
        val day14 : DayForecast = DayForecast(getDate(1665551154000),
            getTime(1665551154000),
            getTime(1665605154000),
            ForecastTemp(20.toFloat(),12.toFloat(),25.toFloat()), 76.toFloat(), 2
        );
        val day15 : DayForecast = DayForecast(getDate(1665637854000),
            getTime(1665637854000),
            getTime(1665691854000),
            ForecastTemp(19.toFloat(),11.toFloat(),25.toFloat()), 77.toFloat(), 1
        );
        val day16 : DayForecast = DayForecast(getDate(1665724554000),
            getTime(1665724554000),
            getTime(1665778554000),
            ForecastTemp(18.toFloat(),10.toFloat(),25.toFloat()), 78.toFloat(), 3
        );

        forecastItemsList.add(day1);
        forecastItemsList.add(day2);
        forecastItemsList.add(day3);
        forecastItemsList.add(day4);
        forecastItemsList.add(day5);
        forecastItemsList.add(day6);
        forecastItemsList.add(day7);
        forecastItemsList.add(day8);
        forecastItemsList.add(day9);
        forecastItemsList.add(day10);
        forecastItemsList.add(day11);
        forecastItemsList.add(day12);
        forecastItemsList.add(day13);
        forecastItemsList.add(day14);
        forecastItemsList.add(day15);
        forecastItemsList.add(day16);

        recyclerAdapter.notifyDataSetChanged()
    }
}
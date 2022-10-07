package com.example.weatherapp
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.view.LayoutInflater

internal class RecyclerAdapter(private var forecastList: List<DayForecast>): RecyclerView.Adapter<RecyclerAdapter.viewHolder>()  {

    internal inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dateTextView: TextView = view.findViewById(R.id.dateTextView)
        var tempTextView: TextView = view.findViewById(R.id.tempTextView)
        var highTextView: TextView = view.findViewById(R.id.highTextView)
        var lowTextView: TextView = view.findViewById(R.id.lowTextView)
        var sunriseTextView: TextView = view.findViewById(R.id.sunriseTextView)
        var sunsetTextView: TextView = view.findViewById(R.id.sunsetTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val forecastItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_card_view, parent, false)
        return viewHolder(forecastItemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val forecastItem = forecastList[position]
        holder.dateTextView.text = forecastItem.date
        val temp = forecastItem.temp.overallTemp.toString()
        holder.tempTextView.text = "Temp: $temp°"
        val min = forecastItem.temp.min.toString()
        holder.lowTextView.text = "Low: $min°"
        val max = forecastItem.temp.max.toString()
        holder.highTextView.text = "High: $max°"
        val sunrise = forecastItem.sunrise
        holder.sunriseTextView.text = "Sunrise: $sunrise am"
        val sunset = forecastItem.sunset
        holder.sunsetTextView.text = "Sunset: $sunset pm"
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }
}

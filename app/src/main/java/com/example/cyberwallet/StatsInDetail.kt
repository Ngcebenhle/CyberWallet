package com.example.cyberwallet

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class StatsInDetail : Fragment() {

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats_in_detail, container, false)

        val pieChart: PieChart = view.findViewById(R.id.pieChart_view)

        // 1. Create Data Entries
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(40f, "Android"))
        entries.add(PieEntry(30f, "iOS"))
        entries.add(PieEntry(20f, "Web"))
        entries.add(PieEntry(10f, "Desktop"))

        // 2. Create DataSet and Style it
        val dataSet = PieDataSet(entries, "Platform Stats")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList() // Use built-in templates
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 15f

        // 3. Setup PieData and update Chart
        val data = PieData(dataSet)
        pieChart.data = data

        // Optional Customization
        pieChart.description.isEnabled = false // Hide description label
        pieChart.centerText = "Platforms"      // Add center text
        pieChart.animateY(1400)              // Add entry animation

        pieChart.invalidate() // Refresh the chart


        val startDate: TextView = view.findViewById(R.id.startDate)
        val endDate: TextView = view.findViewById(R.id.endDate)

        startDate.setOnClickListener {
          //     Start Date, using Date picker
            showDatePicker()

        }

        endDate.setOnClickListener {
            //    End Date, using Date picker
            showDatePicker()

        }
        return view

    }
    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
//                tvSelectedDate.text = "Selected Date: $formattedDate"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()

    }

}
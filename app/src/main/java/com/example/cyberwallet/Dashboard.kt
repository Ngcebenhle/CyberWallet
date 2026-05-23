package com.example.cyberwallet

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class Dashboard : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

       val seeMore : TextView = view.findViewById(R.id.statsDetail)

        seeMore.setOnClickListener {
//            Navigate to Stats in Detail Page
        }

        val lineChart : LineChart = view.findViewById(R.id.lineChart)


//        1. create a list of Entry Points (x,y)
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f,1f))
        entries.add(Entry(1f,3f))
        entries.add(Entry(2f,2f))

        // Wrap entries in a Dataset and customize the line
        val dataSet = LineDataSet(entries, "Monthly Revenue")
        dataSet.color = Color.BLUE
        dataSet.lineWidth = 2f

//        3. Add the DataSet to the Chart
        lineChart.data = LineData(dataSet)
        lineChart.invalidate()


        val categories: Button = view.findViewById(R.id.catButton)
        categories.setOnClickListener {

            //Switch To a different Activity.
            val intent = Intent(requireContext(), Categories::class.java)
            startActivity(intent)

        }

        return view
    }


}


// Chart Data

//        val lineChart: LineChart = findViewById(R.id.lineChart)
//
//        // 1. Create a list of Entries (x, y)
//        // Data must be sorted by X value to avoid rendering errors
//        val entries = mutableListOf<Entry>()
//        entries.add(Entry(0f, 10f))
//        entries.add(Entry(1f, 15f))
//        entries.add(Entry(2f, 8f))
//        entries.add(Entry(3f, 20f))
//
//        // 2. Create LineDataSet and style it
//        val lineDataSet = LineDataSet(entries, "My Data Label")
//        lineDataSet.color = Color.BLUE
//        lineDataSet.setCircleColor(Color.RED)
//        lineDataSet.lineWidth = 2f
//        lineDataSet.circleRadius = 5f
//        lineDataSet.setDrawCircleHole(true)
//        lineDataSet.valueTextSize = 10f
//
//        // 3. Create LineData with the dataset
//        val lineData = LineData(lineDataSet)
//
//        // 4. Set data to chart and refresh
//        lineChart.data = lineData
//        lineChart.description.isEnabled = false // Disable description label
//        lineChart.animateX(1000) // Simple entry animation
//        lineChart.invalidate() // Refreshes the chart
//
//
//
//
//




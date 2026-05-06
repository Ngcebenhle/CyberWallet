package com.example.cyberwallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Dashboard.newInstance] factory method to
 * create an instance of this fragment.
 */
class Dashboard : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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










        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Dashboard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Dashboard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
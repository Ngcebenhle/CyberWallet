package com.example.cyberwallet

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.getValue


class Dashboard : Fragment() {

    var UserId: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)


        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()

        val repository by lazy { UserRepository(db.userDao()) }
        val viewModel: UserViewModel by activityViewModels{
            MyViewModelFactory(repository)
        }

        viewModel.data.observe(viewLifecycleOwner) { message ->
            // Handle your data here
            println(message)

            UserId == message
            Log.d("UserId_in_dash", "${message}")

            val categories: Button = view.findViewById(R.id.catButton)
            categories.setOnClickListener {

                //Switch To a different Activity.
                val intent = Intent(requireContext(), Categories::class.java).apply {
                    putExtra("USER_ID", "${message}")
                }
                startActivity(intent)

            }


            val seeMore : TextView = view.findViewById(R.id.statsDetail)
            seeMore.setOnClickListener {
//            Navigate to Stats in Detail Page

                val intent = Intent(requireContext(), CategoriesView::class.java).apply {
                    putExtra("USER_ID", "${message}")
                }
                startActivity(intent)
            }
        }
        Log.d("UserId_out_dash", "${UserId}")
        println("this works")




        val lineChart : LineChart = view.findViewById(R.id.lineChart)




//        1. create a list of Entry Points (x,y)
        val entries = ArrayList<Entry>()

        viewModel.getAllRecords.observe(viewLifecycleOwner){
            rec ->
            println(rec)
            entries.add(Entry(0f,1f))

        }
//        entries.add(Entry(0f,1f))
//        entries.add(Entry(1f,3f))
//        entries.add(Entry(2f,2f))
//        entries.add(Entry(3f,3f))
//        entries.add(Entry(4f,4f))
//        entries.add(Entry(5f,5f))
//        entries.add(Entry(6f,1f))
//        entries.add(Entry(7f,3f))
//        entries.add(Entry(8f,2f))
//        entries.add(Entry(9f,3f))
//        entries.add(Entry(10f,4f))
//        entries.add(Entry(11f,5f))


        // Wrap entries in a Dataset and customize the line
        val dataSet = LineDataSet(entries, "Monthly Revenue")
        dataSet.setDrawCircles(false)
        dataSet.color = Color.LTGRAY
        dataSet.lineWidth = 0.8f

        // 1. Enable smooth cubic lines
        dataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        // 2. Adjust curvature intensity (0.05f to 1f)
        dataSet.cubicIntensity = 0.4f

        val xAxis: XAxis = lineChart.xAxis
        xAxis.setDrawGridLines(false) // Enable or disable X-axis grid lines
        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.gridLineWidth = 5.0f               // Set the grid line thickness
//        xAxis.gridColor = Color.BLUE           // Set the grid line color
//        xAxis.enableGridDashedLine(10f, 5f, 0f)  // (lineLength, spaceLength, phase) for dashed lines


        val yAxis: YAxis = lineChart.axisLeft
        yAxis.setDrawGridLines(false)             // Enable or disable Y-axis grid lines
        lineChart.axisRight.setDrawLabels(false)//        yAxis.gridLineWidth = 1.0f               // Set thickness
//        yAxis.gridColor = Color.LTGRAY           // Set color


        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.axisRight.setDrawGridLines(false)



//        3. Add the DataSet to the Chart
        lineChart.data = LineData(dataSet)
        lineChart.invalidate()









        return view
    }


}






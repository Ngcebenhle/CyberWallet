package com.example.cyberwallet

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import org.jetbrains.annotations.NotNull
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.getValue
import kotlin.math.log


class StatsInDetail : Fragment() {

    private val calendar = Calendar.getInstance()

    private var startSelectedDate: String? = null
    private var endSelectedDate: String? = null

    var startsDay: Int? = null
    var startYear: Int? = null
    var startMonth: Int? = null


    var endDay: Int? = null
    var endYear: Int? = null
    var endMonth: Int? = null

    // Dates Variables to store.

//    val startsDay = cal.get(Calendar.DAY_OF_MONTH).toString()
//    val startYear = cal.get(Calendar.YEAR).toString()
//    val startMonth = cal.get(Calendar.MONTH).toString()
//
//    val startsDay = cal.get(Calendar.DAY_OF_MONTH).toString()
//    val startYear = cal.get(Calendar.YEAR).toString()
//    val startMonth = cal.get(Calendar.MONTH).toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats_in_detail,
            container, false)

        val pieChart: PieChart = view.findViewById(R.id.pieChart_view)


        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()
        val retrofitApi = RetrofitInstance.RetrofitIN.api


        val repository by lazy { UserRepository(db.userDao(), api = retrofitApi) }
        val viewModel: UserViewModel by activityViewModels{
            MyViewModelFactory(repository)
        }



//        val back: Button = view.findViewById(R.id.backDashboard)
//        back.setOnClickListener {
//            val intent = Intent(requireContext(), Center::class.java)
//            startActivity(intent)
//        }


        val startDate: TextView = view.findViewById(R.id.startDate)
        val endDate: TextView = view.findViewById(R.id.endDate)



        startDate.setOnClickListener {
            var selectedDateStr: String = ""

          // 2. Get the current date to set as the default start date in the picker

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

         // 3. Initialize and display the DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->

                    // Create a Calendar instance with the user's selected date
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay)

                    startMonth = selectedMonth +1
                    startsDay = selectedDay
                    startYear = selectedYear



                    Log.d("date", "${endMonth}")

                    // 4. Format the selected date and store it in your variable
                    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    selectedDateStr = simpleDateFormat.format(selectedCalendar.time)

                    startSelectedDate = selectedDateStr
                    // Optional: Use your variable immediately (e.g., display it in a TextView)
                    startDate.text = selectedDateStr


                },
                year,
                month,
                day
            )

// Show the dialog window
            datePickerDialog.show()


        }

        endDate.setOnClickListener {
            //    End Date, using Date picker
//            showDatePicker()



            var selectedDateStr: String = ""
            // 2. Get the current date to set as the default start date in the picker

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

                 // 3. Initialize and display the DatePickerDialog
               val datePickerDialog = DatePickerDialog(
                   requireContext(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->

                    // Create a Calendar instance with the user's selected date
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay)

                    endMonth = selectedMonth +1
                    endDay = selectedDay
                    endYear = selectedYear


                    Log.d("date", "${endMonth}")

                    // 4. Format the selected date and store it in your variable
                    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    selectedDateStr = simpleDateFormat.format(selectedCalendar.time)

                    endSelectedDate = selectedDateStr
                    // Optional: Use your variable immediately (e.g., display it in a TextView)
                    endDate.text = selectedDateStr


                },
                year,
                month,
                day
            )

// Show the dialog window
            datePickerDialog.show()



        }


        val search : ImageView = view.findViewById(R.id.searchRecords)

        // Recycle view code Here
        viewModel.getAllRecords.observe(viewLifecycleOwner) { users ->
            // Update your UI components, like a RecyclerView adapter

            for (i in users) {

                Log.d("work tryout ", "${i}")
//                data.add(Item(R.drawable.add,
//                    "${i.amount}","${i.description}"))

            }
        }

        // getting the recyclerview by its id
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Item>()

        // This loop will create 20 Views containing
        // the image with the count of view


        viewModel.getAllRecords.observe(viewLifecycleOwner) { users ->
            // Update your UI components, like a RecyclerView adapter

            // Search for the right records and display them here

            //database query here using Dates

            for (i in users) {

                Log.d("work tryout ", "${i}")
                data.add(Item(R.drawable.add,
                    "${i.amount}","${i.description}"))

                // 1. Create Data Entries
                val entries = ArrayList<PieEntry>()

                users.map { i->
                    entries.add(PieEntry(i.amount.toFloat(), i.categoryName))
                }



//                entries.add(PieEntry(30f, "iOS"))
//                entries.add(PieEntry(20f, "Web"))
//                entries.add(PieEntry(10f, "Desktop"))

                // 2. Create DataSet and Style it
                val dataSet = PieDataSet(entries, "Finencial Stats")
                dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList() // Use built-in templates
                dataSet.valueTextColor = Color.BLACK
                dataSet.valueTextSize = 15f

                // 3. Setup PieData and update Chart
                val pieData = PieData(dataSet)
                pieChart.data = pieData

                // Optional Customization
                pieChart.description.isEnabled = false // Hide description label
                pieChart.centerText = "Platforms"      // Add center text
                pieChart.animateY(1400)              // Add entry animation

                pieChart.invalidate() // Refresh the chart


            }

            search.setOnClickListener {

                if(startSelectedDate != "" && endSelectedDate != ""){
                    viewModel.getAllRecords.observe(viewLifecycleOwner) { users ->
                        // Update your UI components, like a RecyclerView adapter

                        for (i in users) {

                            Log.d("work tryout ", "${i}")
//                data.add(Item(R.drawable.add,
//                    "${i.amount}","${i.description}"))

                            // 1. Create Data Entries
                            val entries = ArrayList<PieEntry>()
                            entries.add(PieEntry(40f, "Android"))
                            entries.add(PieEntry(30f, "iOS"))
                            entries.add(PieEntry(20f, "Web"))
                            entries.add(PieEntry(10f, "Desktop"))

                            // 2. Create DataSet and Style it
                            val dataSet = PieDataSet(entries, "Finencial Stats")
                            dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList() // Use built-in templates
                            dataSet.valueTextColor = Color.BLACK
                            dataSet.valueTextSize = 15f

                            // 3. Setup PieData and update Chart
                            val pieData = PieData(dataSet)
                            pieChart.data = pieData

                            // Optional Customization
                            pieChart.description.isEnabled = false // Hide description label
                            pieChart.centerText = "Platforms"      // Add center text
                            pieChart.animateY(1400)              // Add entry animation

                            pieChart.invalidate() // Refresh the chart

                        }
                    }
                }
                else{
                    // tell the user to select dates
                }



            }


        }




        for (i in 1..1) {
            data.add(Item(R.drawable.add,
                "Item", "New------")
            )
        }

        // This will pass the ArrayList to our Adapter
        val adapter = Adapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter



        return view

    }


//    private fun showDatePicker() {
//        // Create a DatePickerDialog
//        val datePickerDialog = DatePickerDialog(
//            requireContext(), { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
//                // Create a new Calendar instance to hold the selected date
//                val selectedDate = Calendar.getInstance()
//                // Set the selected date using the values received from the DatePicker dialog
//                selectedDate.set(year, monthOfYear, dayOfMonth)
//                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
//                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                // Format the selected date into a string
//                val formattedDate = dateFormat.format(selectedDate.time)
//                // Update the TextView to display the selected date with the "Selected Date: " prefix
////                tvSelectedDate.text = "Selected Date: $formattedDate"
//            },
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//
//
//        )
//        // Show the DatePicker dialog
//        datePickerDialog.show()
//
//    }

}
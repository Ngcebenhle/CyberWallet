package com.example.cyberwallet

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Locale
import kotlin.getValue

class newRecord : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private var imageUri: Uri? = null

    private var UserId: Int? = null
    private var selectedDate: Date? = null

    var startsDay: String? = null
    var startYear: String? = null
    var startMonth: String? = null


    var endDay: String? = null
    var endYear: String? = null
    var endMonth: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_record)

        val db = UserDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()

        val repository by lazy { UserRepository(db.userDao()) }
        val viewModel: UserViewModel by viewModels{
            MyViewModelFactory(repository)
        }

        val id = intent.getStringExtra("USER_ID")
        Log.d("userId_in_record", "${id}")

//         val viewModel: UserViewModel by viewModels()

        val reciptImage : ImageView = findViewById(R.id.reciptImage)

        // Register the launcher
        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                // Use the imageUri to display or process the photo
                reciptImage.setImageURI(imageUri)

                // Store the image into a variable
            }
        }



        val amount : EditText = findViewById(R.id.amount)
        val description : EditText = findViewById(R.id.Description)
        val Category : EditText = findViewById(R.id.categoryName)

        val payer : EditText = findViewById(R.id.payer)
        val account : EditText = findViewById(R.id.account)


        val startDate: TextView = findViewById(R.id.startDate)
        val endDate: TextView = findViewById(R.id.endDate)

        startDate.setOnClickListener {
            //     Start Date, using Date picker
//            showDatePicker()

            val cal = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                // Note: 'month' is still 0-indexed here
                val dateString = "$day/${month + 1}/$year"
                // Use dateString...
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()

            startsDay = cal.get(Calendar.DAY_OF_MONTH).toString()
             startYear = cal.get(Calendar.YEAR).toString()
            startMonth = (cal.get(Calendar.MONTH ) + 1).toString()

            startDate.text = "${startsDay}/ ${startMonth} / ${startYear} "

            Log.d("date", startMonth + " " + startYear + " " + startsDay)

//

        }

        endDate.setOnClickListener {
            //    End Date, using Date picker
//            showDatePicker()


            val cal = Calendar.getInstance()


            DatePickerDialog(this, { _, year, month, day ->
                // Note: 'month' is still 0-indexed here
                val dateString = "$day/${month + 1}/$year"
                // Use dateString...
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()

            endDay = cal.get(Calendar.DAY_OF_MONTH).toString()
            endYear = cal.get(Calendar.YEAR).toString()
            endMonth = (cal.get(Calendar.MONTH ) + 1).toString()

            endDate.text = "${endDay}/${endMonth}/ ${endYear}"

            Log.d("date", endMonth+ " " + endYear + " " + endMonth)

        }

        val addImageIcon : ImageView = findViewById(R.id.addImageIcon)
        val saveButton : Button = findViewById(R.id.saveButton)

        addImageIcon.setOnClickListener {

            imageUri = createImageUri()
            imageUri?.let { uri ->
                takePictureLauncher.launch(uri)
            }

//            val intent = Intent(this, MainActivity2::class.java)
//            startActivity(intent)
        }

//        viewModel.data.observe(this){
//                id ->
//            UserId = id
//
//            Log.d("id from viewModel Inside ","${UserId}")
//        }
//
//        Log.d("id from New Record ","${UserId}")

        saveButton.setOnClickListener {

            Log.d("save ", "Start Saved")
//
             val startdateconv = startYear+startMonth+startsDay
            val StartD:Long = startdateconv.toLong()
//
            val enddateconv = endYear+endMonth+endDay
            val endD:Long = enddateconv.toLong()

            viewModel.insertExpense(
                ExpensesTable(
                    userID = id.toString().toInt(),
                    amount = amount.text.toString().toInt(),
                    categoryName = Category.text.toString(),
                    startDate = Date(StartD),
                    endDate = Date(endD),
                    description = description.text.toString(),
                    payer = payer.text.toString(),
                    account = account.text.toString().toInt(),
                    startday = startsDay.toString().toInt(),
                    startmonth = startMonth.toString().toInt(),
                    startyear = startYear.toString().toInt(),
                    endday = endDay.toString().toInt(),
                    endmonth = endMonth.toString().toInt(),
                    endyear = endYear.toString().toInt(),
                    recurring = true,
                    image = imageUri.toString()
                ))
            Log.d("save ", "Saved")

//            val mySwitch = findViewById<Switch>(R.id.switchRec)
//            mySwitch.setOnCheckedChangeListener { _, isChecked ->
//                if (isChecked) {
//                    // Action for ON state
//                    viewModel.insertExpense(
//                        ExpensesTable(
//                            userID = id.toString().toInt(),
//                            amount = amount.text.toString().toInt(),
//                            categoryName = Category.text.toString(),
//                            startDate = Date(2022-5-21),
//                            endDate = Date(2022-5-21),
//                            description = description.text.toString(),
//                            payer = payer.text.toString(),
//                            account = account.text.toString().toInt(),
//                            recurring = true))
//                    Log.d("save ", "Saved")
//
//                }
//                else {
//
//                    // Action for OFF state
//                    viewModel.insertExpense(
//                        ExpensesTable(
//                            userID = id.toString().toInt(),
//                            amount = amount.text.toString().toInt(),
//                            categoryName = Category.text.toString(),
//                            startDate = Date(2022-5-21),
//                            endDate = Date(2022-5-21),
//                            description = description.text.toString(),
//                            payer = payer.text.toString(),
//                            account = account.text.toString().toInt(),
//                            recurring = false))
//
//                    Log.d("save ", "Saved")
//                }
//            }




//            if (amount.text.toString().toInt() != 0 && description.text.toString() != ""){
//
//
//
//                val radioGroup:RadioGroup = findViewById(R.id.category)
//                val selectedId = radioGroup.checkedRadioButtonId
//
//                if (selectedId != -1) {
//                    // Find the RadioButton view by the ID
//                    val radioButton: RadioButton =findViewById(selectedId)
//                    val selectedValue = radioButton.text.toString()
//
//                    viewModel.insertExpense(
//                        ExpensesTable(
//                            userID = 1,
//                            amount = amount.text.toString().toInt(),
//                            categoryName = selectedValue,
//                            startDate = Date(2020-12-13),
//                            endDate = Date(2020-12-13),
//                            description = description.text.toString(),
//                            payer = payer.toString(),
//                            account = account.toString().toInt(),
//                            recurring = true))
//
//                    Log.d("save ", "Saved")
//
//                }
//            }

            //Switch Pages
            val intent = Intent(this, Categories::class.java).apply {
                putExtra("USER_ID",id)
            }
            startActivity(intent)
        }

    }

    private fun createImageUri(): Uri? {
        val imageFile = File(this.filesDir, "camera_photo.jpg")
        return FileProvider.getUriForFile(
            this,
            "${this.packageName}.fileprovider",
            imageFile
        )
    }

    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
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
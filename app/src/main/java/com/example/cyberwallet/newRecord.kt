package com.example.cyberwallet

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale
import kotlin.getValue

class newRecord : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private var imageUri: Uri? = null


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

//         val viewModel: UserViewModel by viewModels()

        val reciptImage : ImageView = findViewById(R.id.reciptImage)

        // Register the launcher
        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                // Use the imageUri to display or process the photo
                reciptImage.setImageURI(imageUri)
            }
        }



        val amount : EditText = findViewById(R.id.amount)
        val description : EditText = findViewById(R.id.Description)
        val startDate : EditText = findViewById(R.id.startDate)
        val endDate : EditText = findViewById(R.id.endDate)

        startDate.setOnClickListener {
            showDatePicker()
        }

        endDate.setOnClickListener {
            showDatePicker()
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



        saveButton.setOnClickListener {

//          val  data  =  viewModel.data.observe(this){ d->
//              Log.d("userId", "${d}")
//          }



                viewModel.insertExpense(ExpensesTable(
                    userID = 1,
                    categoryName = "Transport",
                    startDate = Date(2020-12-13),
                    endDate = Date(2020-12-13),
                    description = description.text.toString(),
                    payer = "mom",
                    account = 2233837,
                    recurring = true))
//
//            lifecycleScope.launch {
//                userDao.insertExpense(
//                    ExpensesTable(userID = 1,
//                    categoryName = "Transport",
//                    startDate = Date(2020-12-13),
//                    endDate = "20/03/2020",
//                    description = "This is a test",
//                    payer = "mom",
//                    account = 2233837,
//                    recurring = true),)
//
//            }


            //Switch Pages
            val intent = Intent(this, Categories::class.java)
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
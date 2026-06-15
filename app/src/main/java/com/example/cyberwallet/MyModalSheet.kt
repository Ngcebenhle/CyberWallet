package com.example.cyberwallet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.getValue

class MyModalSheet : BottomSheetDialogFragment() {


    var UserId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_modal_bottom_sheet, container, false)



        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()


        val repository by lazy { UserRepository(db.userDao()) }
        val viewModel: UserViewModel by activityViewModels{
            MyViewModelFactory(repository)
        }





        val catName : EditText = view.findViewById<EditText>(R.id.catName)
        val max : EditText = view.findViewById<EditText>(R.id.MaxGoalAmount)
        val min : EditText = view.findViewById<EditText>(R.id.MinGoalAmount)

        val radioGroup:RadioGroup = view.findViewById(R.id.radioGroup)



        // Setup dismiss button
        view.findViewById<Button>(R.id.btn_dismiss).setOnClickListener {

            if(catName.text.toString() != ""){

                //Get Selected RadioButton
                val selectedId = radioGroup.checkedRadioButtonId

                if (selectedId != -1) {
                    // Find the RadioButton view by the ID
                    val radioButton: RadioButton = view.findViewById(selectedId)
                    val selectedValue = radioButton.text.toString()

                    Log.d("work", "${selectedValue}")


                    viewModel.data.observe(viewLifecycleOwner) { id ->
                        // Handle your data here
                        println(id)
                        Log.d("UserId_in_modal", "${id}")

                        //            Insert Data here into Database
                        lifecycleScope.launch {

                            userDao.insertCategory(
                                Caregories(
                                    UserID = id.toString().toInt(),
                                    InorEx = selectedValue,
                                    CategoryName = catName.text.toString(),
                                    MaxGoalAmount = max.text.toString().toInt(),
                                    MinGoalAmount = min.text.toString().toInt(),
                                    dateCreated = ""

                                ))

                        }


                    }
                    Log.d("UserId_out_stat", "its a dud")




                }

            }
            else {
                // Display error massage for user to enter name
                dismiss() // Close the modal
            }

            viewModel.data.observe(viewLifecycleOwner){
                    id ->
                UserId = id

                Log.d("id_in_Modal","${UserId}")
            }

            Log.d("id_in_Modal_inside","${UserId}")



            dismiss() // Close the modal
        }



        return view
    }

    companion object {
        const val TAG = "MyModalSheet"
    }
}

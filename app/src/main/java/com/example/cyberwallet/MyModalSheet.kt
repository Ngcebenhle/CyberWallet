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
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.util.Date

class MyModalSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_modal_bottom_sheet, container, false)

        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()

        val catName : EditText = view.findViewById<EditText>(R.id.catName)

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

                    //            Insert Data here into Database
                    lifecycleScope.launch {

                        userDao.insertCategory(Caregories(UserID = 1, InorEx = selectedValue, CategoryName = catName.text.toString(),
                            dateCreated = ""
                        ))

                    }

                }

            }
            else {
                // Display error massage for user to enter name
                dismiss() // Close the modal
            }



            dismiss() // Close the modal
        }

        return view
    }

    companion object {
        const val TAG = "MyModalSheet"
    }
}

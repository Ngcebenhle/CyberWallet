package com.example.cyberwallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

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

        // Setup dismiss button
        view.findViewById<Button>(R.id.btn_dismiss).setOnClickListener {

//            Insert Data here into Database
        lifecycleScope.launch {

            userDao.insertCategory(Caregories(UserID = 1, CategoryName = catName.text.toString(),
                dateCreated = "2020/03/23"))

        }

            dismiss() // Close the modal
        }

        return view
    }

    companion object {
        const val TAG = "MyModalSheet"
    }
}

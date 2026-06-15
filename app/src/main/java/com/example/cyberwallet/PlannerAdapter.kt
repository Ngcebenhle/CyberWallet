package com.example.cyberwallet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class PlannerAdapter(private val list: List<events>,
//              private val listener: adaptarEventListener
):
    RecyclerView.Adapter<PlannerAdapter.ViewHolder>(){


    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.plannercard, p0, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(item.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = item.text

    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {


//        val imageView: ImageView = itemView.findViewById(R.id.addButton)
        val textView: TextView = itemView.findViewById(R.id.target)








//        init {
//            saveButton.setOnClickListener(this)
//        }
//
//        override fun onClick(p0: View?) {
//            val position = adapterPosition
//            if(position != RecyclerView.NO_POSITION){
//
//                listener.onClickSaveButton(position)
//
//            }
//        }



    }


}


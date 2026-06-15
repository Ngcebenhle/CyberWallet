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

class Adapter(private val list: List<Item>,
//              private val listener: adaptarEventListener
):
    RecyclerView.Adapter<Adapter.ViewHolder>(){


    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.card_view_design, p0, false)


        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(item.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = item.text

        holder.CategoryName.text = item.catName



//        holder.form.visibility = View.GONE
        holder.extendButton.setOnClickListener {
//
            if(holder.form.visibility == View.GONE ){

                holder.form.visibility = View.VISIBLE
//                holder.amount.visibility = View.VISIBLE
                holder.extendButton.setImageResource(R.drawable.arrow_drop_up)


//                holder.saveButton.setOnClickListener {
//
//
//
//                   // Saving data to the database
////                    lifecycleScope.launch {
////                        userDao.insert(User(name = "tim", password = "987654321"))
////                        userDao.insert(User(name = "paul", password = "2468"))
////                        userDao.insert(User(name = "ann", password = "723454"))
////                        userDao.insert(User(name = "moss", password = "887654"))
////
////                    }
//
////                    holder.amount.text.toString()
////                    holder.description.text.toString()
////                    holder.startDate.visibility = View.VISIBLE
////                    holder.endDate.visibility = View.VISIBLE
////                    holder.reciptImage.visibility = View.VISIBLE
////                    holder.addImageIcon.visibility = View.VISIBLE
////                    holder.form.visibility = View.VISIBLE
//
//                }

            }
            else{
                holder.form.visibility = View.GONE
                holder.extendButton.setImageResource(R.drawable.add)
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
//        ,
//        View.OnClickListener
          {



        val imageView: ImageView = itemView.findViewById(R.id.addButton)
        val textView: TextView = itemView.findViewById(R.id.target)



        // form
        val extendButton : ImageView = itemView.findViewById(R.id.addButton)
        val form : ConstraintLayout = itemView.findViewById(R.id.addExpenseOrIncomeForm)


        // List of form CV
        val amount : EditText = itemView.findViewById(R.id.amount)
        val description : EditText = itemView.findViewById(R.id.Description)
        val startDate : EditText = itemView.findViewById(R.id.startDate)
        val endDate : EditText = itemView.findViewById(R.id.endDate)
        val reciptImage : ImageView = itemView.findViewById(R.id.reciptImage)
        val addImageIcon : ImageView = itemView.findViewById(R.id.addImageIcon)
        val saveButton : Button = itemView.findViewById(R.id.saveButton)


        val CategoryName : TextView = itemView.findViewById(R.id.catName)



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

    interface adaptarEventListener {

        // create callback function
        fun onClickSaveButton(position: Int)
    }
}


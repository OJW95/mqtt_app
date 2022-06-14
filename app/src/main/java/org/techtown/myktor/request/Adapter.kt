package org.techtown.myktor.request

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.myktor.R
import org.techtown.myktor.data.ChatMessage

class Adapter(private val dataset: ArrayList<ChatMessage>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTopic: TextView = view.findViewById(R.id.tv_topic)
        val tvValue: TextView = view.findViewById(R.id.tv_value)
        val list: LinearLayout = view.findViewById(R.id.item_list)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("RtlHardcoded")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTopic.text = dataset[position].id
        viewHolder.tvValue.text = dataset[position].content

        if(dataset[position].id == "user") { // 본인 채팅
            viewHolder.list.gravity = Gravity.RIGHT
            viewHolder.tvValue.gravity = Gravity.RIGHT
        }
        else {
            viewHolder.list.gravity = Gravity.LEFT
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataset.size
    }
}
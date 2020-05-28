package com.allenwang.zoo.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allenwang.zoo.R
import com.allenwang.zoo.pojo.Park
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_screen_item.view.*

class MainScreenAdapter (var parks: List<Park>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return parks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.main_screen_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val park = parks.get(position)
        holder.titleTextView.text = park.eName
        holder.descriptionTextView.text = park.eInfo
        Picasso.with(context)
            .load(park.ePicURL)
            .into(holder.photoImageView)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.textView_title
    val descriptionTextView: TextView = view.textView_description
    val photoImageView: ImageView = view.imageView_photo

}
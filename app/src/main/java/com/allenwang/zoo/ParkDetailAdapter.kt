package com.allenwang.zoo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allenwang.zoo.R
import com.allenwang.zoo.pojo.Park
import com.allenwang.zoo.pojo.Plant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_screen_item.view.*

class ParkDetailAdapter(var plants: List<Plant>, private val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.plan_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val park = plants[position]
        val defualt = "https://images.pexels.com/photos/1072824/pexels-photo-1072824.jpeg?cs=srgb&dl=person-holding-a-green-plant-1072824.jpg&fm=jpg"
        val url = if(park.fPic01URL.isEmpty()) defualt else park.fPic01URL
        Picasso.with(context)
            .load(url)
            .into(holder.photoImageView)
    }

    fun getItem(position: Int): Plant {
        return plants[position]
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val photoImageView: ImageView = view.imageView_photo

}
package com.allenwang.zoo

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.allenwang.zoo.main.MainScreenAdapter
import com.allenwang.zoo.main.RecyclerItemClickListener
import com.allenwang.zoo.pojo.Park
import com.allenwang.zoo.pojo.Plant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PlantDetailFragment : Fragment() {
    lateinit var plant: Plant

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            plant = bundle.getParcelable<Plant>("plant") as Plant
        }

        textView_description.text = plant.fSummary
        Picasso.with(context).load(plant.fPic01URL).into(imageView_photo)
    }
}

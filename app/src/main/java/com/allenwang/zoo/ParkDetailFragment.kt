package com.allenwang.zoo

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.allenwang.zoo.pojo.Park
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ParkDetailFragment : Fragment() {
    lateinit var park: Park

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            park = bundle.getParcelable<Park>("park") as Park
        }

        textView_description.text = park.eInfo
        Picasso.with(context).load(park.ePicURL).into(imageView_photo)
    }
}

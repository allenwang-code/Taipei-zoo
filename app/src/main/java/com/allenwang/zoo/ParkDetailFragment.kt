package com.allenwang.zoo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.allenwang.zoo.main.RecyclerItemClickListener
import com.allenwang.zoo.pojo.Park
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ParkDetailFragment : Fragment() {
    lateinit var park: Park
    private lateinit var adapter: ParkDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayShowHomeEnabled(true)
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

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        horizontal_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ParkDetailAdapter(park.plants, context!!)
        horizontal_recyclerView.adapter = adapter

        horizontal_recyclerView.addOnItemTouchListener(object :
            RecyclerItemClickListener(context!!, horizontal_recyclerView, object :
                OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val plant = adapter.getItem(position)
                    val bundle = Bundle()
                    bundle.putParcelable("plant", plant)
                    findNavController().navigate(
                        R.id.action_SecondFragment_to_ThirdFragment,
                        bundle
                    )
                }

                override fun onLongItemClick(view: View?, position: Int) {

                }
            }) {})

    }
}

package com.allenwang.zoo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.allenwang.zoo.R
import com.allenwang.zoo.pojo.Park
import kotlinx.android.synthetic.main.fragment_first.*


class MainFragment : Fragment(), MainScreenContract.StoryView {
    private val presenter = MainScreenPresenter()
    private lateinit var adapter: MainScreenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        presenter.bind(this)
        presenter.getParks()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun showParks(parks: List<Park>) {
        adapter.parks = parks
        adapter.notifyDataSetChanged()
    }

    override fun showProgressBar(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        park_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = MainScreenAdapter(arrayListOf(), context!!)
        park_recycler_view.adapter = adapter

        park_recycler_view.addOnItemTouchListener(object :
            RecyclerItemClickListener(context!!, park_recycler_view, object :
                OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val park = adapter.getItem(position)
                    val bundle = Bundle()
                    bundle.putParcelable("park", park)
                    findNavController().navigate(
                        R.id.action_FirstFragment_to_SecondFragment,
                        bundle
                    )
                }

                override fun onLongItemClick(view: View?, position: Int) {

                }
            }) {})

    }

}

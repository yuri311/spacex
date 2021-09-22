package com.yuri.spacex.fragments

import android.content.Context
import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuri.spacex.MainActivity
import com.yuri.spacex.viewmodels.LaunchesViewModel
import com.yuri.spacex.R
import com.yuri.spacex.adapters.LaunchListAdapter
import com.yuri.spacex.dataobjects.Launch


class LaunchListFragment : Fragment(), View.OnClickListener {
    private val model: LaunchesViewModel by activityViewModels()
    private var adapter = LaunchListAdapter(this)
    private lateinit var callbacks: LaunchListInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = activity as LaunchListInterface
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setHasOptionsMenu(true)

        setupRecyclerView(view.findViewById(R.id.item_list))
        model.getLaunchListLiveData().observe(viewLifecycleOwner, { launchList ->
            adapter.refreshList(launchList)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_launch_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_by_date) {
            model.sortByDate()
        } else if (item.itemId == R.id.sort_by_status) {
            model.sortByStatus()
        }
        return true
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(context);
        recyclerView.adapter = adapter
    }

    override fun onClick(v: View?) {
        callbacks.onLaunchSelected(v?.tag as Launch)
    }

    interface LaunchListInterface {
        fun onLaunchSelected(launch: Launch)
    }
}

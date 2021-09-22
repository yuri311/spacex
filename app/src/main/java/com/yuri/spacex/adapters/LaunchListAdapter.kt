package com.yuri.spacex.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yuri.spacex.fragments.LaunchListFragment
import com.yuri.spacex.R
import com.yuri.spacex.dataobjects.Launch

class LaunchListAdapter(parentFragment: LaunchListFragment) :
    RecyclerView.Adapter<LaunchListAdapter.ViewHolder>() {
    var launchList: List<Launch> = emptyList()
    private val listener = parentFragment as View.OnClickListener

    fun refreshList(launchList: List<Launch>) {
        this.launchList = launchList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = launchList[position]
        holder.txtFlightNumber.text = item.flightNumber
        holder.txtName.text = item.name
        holder.txtDate.text = item.dateUtc
        holder.chkSuccess.isChecked = item.success

        with(holder.itemView) {
            tag = item
            setOnClickListener(listener)
        }
    }

    override fun getItemCount() = launchList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtFlightNumber: TextView = view.findViewById(R.id.txt_flight_number)
        val txtName: TextView = view.findViewById(R.id.txt_name)
        val txtDate: TextView = view.findViewById(R.id.txt_time)
        val chkSuccess: CheckBox = view.findViewById(R.id.chk_success)
    }
}

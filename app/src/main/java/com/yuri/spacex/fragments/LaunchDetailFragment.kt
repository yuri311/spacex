package com.yuri.spacex.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yuri.spacex.viewmodels.LaunchesViewModel
import com.yuri.spacex.R


class LaunchDetailFragment : Fragment() {
    private lateinit var txtDetails: TextView
    private lateinit var txtName: TextView
    private lateinit var txtRocketName: TextView
    private lateinit var txtCost: TextView

    private val model: LaunchesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        txtName = view.findViewById(R.id.txt_name)
        txtDetails = view.findViewById(R.id.txt_details)
        txtRocketName = view.findViewById(R.id.txt_rocket_name)
        txtCost = view.findViewById(R.id.txt_costs)
        
        model.getLaunchDetailsLiveData().observe(viewLifecycleOwner, { launchDetail ->
            launchDetail?.let { launch ->
                txtName.text = launch.name
                txtDetails.text = launchDetail.details
            }
        })

        model.getRocketLiveData().observe(viewLifecycleOwner, { rocketDetail ->
            rocketDetail?.let { rocket ->
                txtRocketName.text = rocket.name
                txtCost.text = rocket.cost.toString()
            }
        })
    }
}

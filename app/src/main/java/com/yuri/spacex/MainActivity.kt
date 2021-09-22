package com.yuri.spacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.yuri.spacex.dataobjects.Launch
import com.yuri.spacex.fragments.LaunchDetailFragment
import com.yuri.spacex.fragments.LaunchListFragment
import com.yuri.spacex.viewmodels.LaunchesViewModel


class MainActivity : AppCompatActivity(), LaunchListFragment.LaunchListInterface {
    val model: LaunchesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, LaunchListFragment(), "launch_list")
            .commit()
    }

    override fun onLaunchSelected(launch: Launch) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, LaunchDetailFragment(), "launch_detail")
            .addToBackStack(null)
            .commit()

        launch.launchId?.let { model.fetchLaunch(it) }
        launch.rocket?.let { model.fetchRocket(it) }
    }
}
package com.assignment.kotlinmvvm

import android.app.ProgressDialog

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.assignment.kotlinmvvm.DataModel.FeedModel
import com.assignment.kotlinmvvm.DataModel.FeedRowsModel

import com.assignment.trainenquiry.adapter.FeedAdapter
import com.feedsample.R
import com.feedsample.ViewModels.FeedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.hasFixedSize()


            callFeedApi()


    }

    private fun callFeedApi() {
        val progressDialog = ProgressDialog(this@MainActivity)

        progressDialog.setMessage("loading data, please wait")
        progressDialog.show()

        val mAndroidViewModel = ViewModelProviders.of(this@MainActivity).get(FeedViewModel::class.java)
        mAndroidViewModel.getFeedData()?.observe(this, Observer<FeedModel> { feedList ->
            progressDialog.dismiss()
            getSupportActionBar()?.setTitle(feedList?.title);

            recyclerView.adapter = FeedAdapter(this@MainActivity, feedList?.rows as ArrayList<FeedRowsModel>)
        })

    }


}


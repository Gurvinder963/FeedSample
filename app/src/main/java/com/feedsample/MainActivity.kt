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



        if (isNetworkConnected()) {
            callFeedApi()
        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }


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
    private fun isNetworkConnected(): Boolean {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val networkCapabilities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.getNetworkCapabilities(activeNetwork)
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
    }

}


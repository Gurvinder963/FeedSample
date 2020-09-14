package com.feedsample.CommonService


import androidx.lifecycle.MutableLiveData
import com.assignment.kotlinmvvm.DataModel.FeedModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedService {



    fun loadFeedData(): MutableLiveData<FeedModel>? {

     return null
    }
}

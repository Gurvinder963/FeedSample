package com.feedsample.CommonService


import androidx.lifecycle.MutableLiveData
import com.assignment.kotlinmvvm.DataModel.FeedModel
import com.feedsample.interfaces.ApiInterface
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedService {

    val liveUserResponse: MutableLiveData<FeedModel> = MutableLiveData()

    companion object Factory {
        const val BASE_URL = "https://dl.dropboxusercontent.com/"
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadFeedData(): MutableLiveData<FeedModel>? {

     return null
    }
}

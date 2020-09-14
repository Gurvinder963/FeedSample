package com.feedsample.interfaces

import com.assignment.kotlinmvvm.DataModel.FeedModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun feedRepositories(): Call<FeedModel>

}
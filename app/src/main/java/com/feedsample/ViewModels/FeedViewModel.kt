package com.feedsample.ViewModels



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.kotlinmvvm.DataModel.FeedModel
import com.feedsample.CommonService.FeedService

class FeedViewModel: ViewModel() {

    private val mService  =  FeedService()

    fun getFeedData() : MutableLiveData<FeedModel>? {
        return mService.loadFeedData()
    }

}

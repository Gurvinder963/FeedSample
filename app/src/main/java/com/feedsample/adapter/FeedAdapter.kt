package com.assignment.trainenquiry.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.kotlinmvvm.DataModel.FeedRowsModel
import com.assignment.kotlinmvvm.MainActivity


import com.bumptech.glide.Glide

import com.feedsample.R
import kotlinx.android.synthetic.main.items.view.*

class FeedAdapter(var context: MainActivity, var mFeedList: ArrayList<FeedRowsModel>): RecyclerView.Adapter<FeedAdapter.EmpHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return EmpHolder(view)
    }

    override fun getItemCount(): Int {
        return mFeedList.size
    }

    override fun onBindViewHolder(holder:EmpHolder, position: Int) {

        holder.tvTitle?.text = mFeedList[position].title
        holder.tvDescription?.text = mFeedList[position].description
        val media =  mFeedList[position].imageHref
        if (media !== null) {
            Glide.with(context)
                    .load(media)
                    .into(  holder.ivItemImage)
        }
        else{
            holder.ivItemImage.visibility=View.GONE
        }

    }


    class EmpHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tvTitle
        val tvDescription = view.tvDescription
        val ivItemImage = view.ivItemImage
    }

}
package com.icoo.androidstudy.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.icoo.androidstudy.R
import com.icoo.androidstudy.data.model.GithubRepo


class RepoRecyclerViewAdapter(val ctx: Context, val data: ArrayList<GithubRepo>)
    : RecyclerView.Adapter<RepoRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.item_repo, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = data[position].name
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.item_repo_name) as TextView
    }
}
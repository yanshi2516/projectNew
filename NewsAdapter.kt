package com.example.projectnews.newstories


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectnews.R
import com.google.android.material.snackbar.Snackbar

interface RecyclerViewClickListener
{
    fun onClick(view : View, position : Int)
}

class NewsAdapter(mListener: RecyclerViewClickListener) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>()  {
    private var newsData = ArrayList<News>()
    private var myListener = mListener

    class MyViewHolder(itemView: View,mListener: RecyclerViewClickListener) : RecyclerView.ViewHolder(itemView) {

        var cardView : CardView = itemView.findViewById(R.id.cardViewAllNews)
        var textTitle : TextView = itemView.findViewById(R.id.textViewTitle)
        var textBy : TextView = itemView.findViewById(R.id.textViewBy)
        var textTime : TextView = itemView.findViewById(R.id.textViewTime)
        var myListener : RecyclerViewClickListener = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.news_adapter,parent,false)
        return MyViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            textTitle.text = newsData[position].title
            textBy.text = newsData[position].by
            textTime.text = newsData[position].time.toString()
        }
        holder.cardView.setOnClickListener {
            holder.myListener.onClick(holder.itemView,position)
        }
    }
    override fun getItemCount() = newsData.size

    fun setNews(newsDataList : ArrayList<News>)
    {
        newsData.clear()
        newsData.addAll(newsDataList)
        notifyDataSetChanged()
    }
}


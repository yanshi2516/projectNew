package com.example.projectnews.newstories

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectnews.R
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import java.io.Serializable


class FragmentNews(private val storiesType: String) : Fragment() {
    private lateinit var model: NewsViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var rootView : View
    private lateinit var newsAdapter : NewsAdapter
    private lateinit var topRecyclerView : RecyclerView
    private lateinit var progressBar : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        model = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    @ImplicitReflectionSerializer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_news, container, false)
        progressBar = rootView.findViewById(R.id.progressBar) as ProgressBar
        topRecyclerView = rootView.findViewById(R.id.topRecyclerView) as RecyclerView
        viewManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(object : RecyclerViewClickListener{
            override fun onClick(newsDetailData : News) {
                val intent = Intent(context,NewsDataActivity::class.java)
                intent.putExtra("newsDetailsData",newsDetailData)
                context?.startActivity(intent)
            }
        })
        topRecyclerView.adapter = newsAdapter
        topRecyclerView.layoutManager = viewManager
        return rootView
    }
    @UnstableDefault
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.newsDataList.observe(this, Observer {
            newsAdapter.setNews(it)
        })

        model.newsErrorMessage.observe(this, Observer {
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        })

        model.isProgressLoading.observe(this, Observer {
            if(it) {
                progressBar.visibility = View.VISIBLE
            }
            else {
                progressBar.visibility = View.GONE
            }
        })
        model.getNewsDataFromRepository(storiesType)

    }



    companion object{
        fun getInstance(storiesType : String) : FragmentNews = FragmentNews(storiesType)
    }
}



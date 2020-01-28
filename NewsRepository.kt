package com.example.projectnews.newstories

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.UnstableDefault
import retrofit2.Call
import retrofit2.Response


interface OnNewsDataFetchListener {
    fun onNewsFetch(listOfNews: ArrayList<News>)
    fun onNewsError(errorMessage: String?)
}

class NewsRepository {

    @UnstableDefault
    val remoteDataSource = RemoteDataSource()
    val newsData = ArrayList<News>()

    @UnstableDefault
    fun getNewsData(storiesType : String,onNewsDataFetchListener: OnNewsDataFetchListener) {
            remoteDataSource.getId(storiesType).enqueue(object : retrofit2.Callback<List<Int>> {
                override fun onFailure(call: Call<List<Int>>?, t: Throwable?) {
                    t?.printStackTrace()
                }
                override fun onResponse(call: Call<List<Int>>?, response: Response<List<Int>>?) {
                    val listOfIds = response?.body()
                    getNewsDataFromId(listOfIds, onNewsDataFetchListener)
                }
            })
    }
    @UnstableDefault
    fun getNewsDataFromId(listOfIds: List<Int>?, onNewsDataFetchListner: OnNewsDataFetchListener) {
        for (i in 0..9) {
            val topNewsIdData = remoteDataSource.newsServiceData(listOfIds!![i])
            topNewsIdData.enqueue(object : retrofit2.Callback<News> {
                override fun onFailure(call: Call<News>?, t: Throwable) {
                    onNewsDataFetchListner.onNewsError(t.message)
                    t.printStackTrace()
                }
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    newsData.add(response.body() as News)
                    if (i == 9) {
                        onNewsDataFetchListner.onNewsFetch(newsData)
                    }
                }
            })
        }
    }
}



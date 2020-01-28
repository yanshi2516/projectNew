package com.example.projectnews.newstories

import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.UnstableDefault
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService{
    @GET("{stories}.json")
    fun listTopStories(@Path("stories") stories : String) :   Call<List<Int>>

    @GET("item/{item}.json")
    fun listTopNewsIdData(@Path("item") item: Int?) : Call<News>
}

@UnstableDefault
class RemoteDataSource {
    private val service = retrofit.create<NewsService>(NewsService::class.java)

    fun getId(storiesType : String): Call<List<Int>> {
        return service.listTopStories(storiesType)
    }

    fun newsServiceData(newsId : Int) : Call<News>{
        return service.listTopNewsIdData(newsId)
    }
}

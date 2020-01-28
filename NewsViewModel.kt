package com.example.projectnews.newstories

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.serialization.UnstableDefault

class NewsViewModel : ViewModel() {
    var newsDataList = MutableLiveData<ArrayList<News>>()
    var isProgressLoading = MutableLiveData<Boolean>(false)
    var newsErrorMessage = MutableLiveData<String>()
    private val repo = NewsRepository()

    @UnstableDefault
    fun getNewsDataFromRepository(storiesType : String) {
        isProgressLoading.value = true
        repo.getNewsData(storiesType,object : OnNewsDataFetchListener {
            override fun onNewsFetch(listOfNews: ArrayList<News>) {
                newsDataList.value = listOfNews
                isProgressLoading.value = false
            }
            override fun onNewsError(errorMessage : String?) {
                newsErrorMessage.value = errorMessage
                isProgressLoading.value = false
            }
        })
    }
}

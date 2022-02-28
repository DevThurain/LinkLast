package com.thurainx.linklast.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thurainx.linklast.persistance.LinkEntity
import com.thurainx.linklast.persistance.repository.LinkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject


@HiltViewModel
class LinkViewModel @Inject constructor(
    val linkRepository: LinkRepositoryImpl
) : ViewModel() {
    var linkState by mutableStateOf(LinkState())


    init {
        viewModelScope.launch {
            linkRepository.getAllLinks().collectLatest {
                linkState = linkState.copy(
                    linkList = it
                )
            }
        }
    }

    fun insertLink(linkEntity: LinkEntity) {
        viewModelScope.launch {
            linkRepository.insertLink(linkEntity = linkEntity)
        }
    }

    fun deleteLink(linkEntity: LinkEntity) {
        viewModelScope.launch {
            linkRepository.deleteLink(linkEntity = linkEntity)
        }
    }

    fun fetchWebsiteLogo() {
        viewModelScope.launch {
            try {
                val doc: Document = withContext(Dispatchers.IO){
                    Jsoup.connect("https://en.wikipedia.org/").get()
                }
                //log(doc.title())
                val newsHeadlines: Elements = doc.select("#mp-itn b a")
                for (headline in newsHeadlines) {
                    Log.d(
                        "url",
                        headline.attr("title"),
                    )
                }
            } catch (e: Exception) {
                Log.d("error", e.toString())
            }
        }

    }



}
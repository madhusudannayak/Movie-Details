package com.example.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.moviedetails.remote.MovieInterface
import com.example.moviedetails.ui.movie.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val movieInterface: MovieInterface) : ViewModel() {

    private val query = MutableLiveData<String>("")
    val list = query.switchMap {
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(it, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }
    fun setQuery(s:String){
        query.postValue(s)
    }

}
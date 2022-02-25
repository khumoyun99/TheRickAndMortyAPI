package com.example.paging3therickandmortyapi.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paging3therickandmortyapi.repository.RickMortyRepository
import com.example.paging3therickandmortyapi.utils.NetworkHelper

class ViewModelFactory(
//    private val context: Context,
//    private val networkHelper: NetworkHelper,
//    private val rickMortyRepository: RickMortyRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RickMortyPagingModel::class.java)){
            return RickMortyPagingModel()  as T
        }else{
            throw Exception("Error")
        }
    }
}
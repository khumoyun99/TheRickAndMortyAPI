package com.example.paging3therickandmortyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3therickandmortyapi.model.Result
import kotlinx.coroutines.flow.Flow

class RickMortyPagingModel:ViewModel() {

    fun  flowData(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
            maxSize = 200
        ), pagingSourceFactory = {
            PagingDataSource()
        }).flow.cachedIn(viewModelScope)

    }
}
package com.example.paging3therickandmortyapi.repository

import com.example.paging3therickandmortyapi.database.RickMortyDao
import com.example.paging3therickandmortyapi.database.RickMortyEntity
import com.example.paging3therickandmortyapi.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class RickMortyRepository(
    val apiService: ApiService,
//    val rickMortyDao: RickMortyDao
) {

//    suspend fun getAllRickMortyData() = flow { emit(apiService.getAllRickMortyData()) }
//
//    suspend fun addAllRickMortyData(list:List<RickMortyEntity>) = rickMortyDao.addAllRickMortyList(list)
//
//    suspend fun getDbAllRickMortyData() = flow { emit(rickMortyDao.getAllRickMortyData()) }
//
//    suspend fun deleteRickMortyData(rickMortyEntity: RickMortyEntity) = flow {emit(rickMortyDao.deleteRickMortyData(rickMortyEntity))  }

    //paging
    suspend fun getAllRickMortyDataPaging(page:Int) = flow { emit(apiService.getAllRickMortyDataPaging(page = page)) }



}
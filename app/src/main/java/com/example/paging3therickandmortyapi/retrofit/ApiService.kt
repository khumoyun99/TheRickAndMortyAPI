package com.example.paging3therickandmortyapi.retrofit

import com.example.paging3therickandmortyapi.model.Result
import com.example.paging3therickandmortyapi.model.RickandMortyApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getAllRickMortyData():Response<RickandMortyApiData>

    @GET("character/")
    suspend fun getAllRickMortyDataPaging(
        @Query("page")page:Int
    ):RickandMortyApiData

}
package com.example.paging3therickandmortyapi.retrofit

import com.example.paging3therickandmortyapi.model.RickandMortyApiData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(".")
    suspend fun getAllRickMortyData():Response<RickandMortyApiData>

}
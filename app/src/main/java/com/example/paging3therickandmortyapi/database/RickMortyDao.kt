package com.example.paging3therickandmortyapi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RickMortyDao {

    @Insert
    fun addRickMortyData(rickMortyEntity: RickMortyEntity)

    @Delete
    suspend fun deleteRickMortyData(rickMortyEntity: RickMortyEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addAllRickMortyList(arrayList: List<RickMortyEntity>)

    @Query("select * from rickmortyentity")
    suspend fun getAllRickMortyData():List<RickMortyEntity>
}
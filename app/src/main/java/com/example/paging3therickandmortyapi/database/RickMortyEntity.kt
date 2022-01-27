package com.example.paging3therickandmortyapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.paging3therickandmortyapi.model.Location
import com.example.paging3therickandmortyapi.model.Origin

@Entity
class RickMortyEntity(
    @PrimaryKey(autoGenerate = true)
    val idRick:Int=0,
    val gender: String,
    val id: Int,
    val image: String,
    val location: String,
    val episode: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
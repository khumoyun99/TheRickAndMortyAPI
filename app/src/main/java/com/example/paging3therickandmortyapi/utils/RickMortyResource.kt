package com.example.paging3therickandmortyapi.utils

import com.example.paging3therickandmortyapi.database.RickMortyEntity

sealed class RickMortyResource{

    object Loading: RickMortyResource()

    class Success(val list: List<RickMortyEntity>?): RickMortyResource()

    class Error(val message:String): RickMortyResource()
}

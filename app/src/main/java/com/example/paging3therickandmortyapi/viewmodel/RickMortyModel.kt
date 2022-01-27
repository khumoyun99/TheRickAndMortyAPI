package com.example.paging3therickandmortyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paging3therickandmortyapi.database.RickMortyEntity
import com.example.paging3therickandmortyapi.repository.RickMortyRepository
import com.example.paging3therickandmortyapi.utils.NetworkHelper
import com.example.paging3therickandmortyapi.utils.RickMortyResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RickMortyModel(
    private val rickMortyRepository: RickMortyRepository,
    private val networkHelper: NetworkHelper
):ViewModel() {


    fun getAllRickMortyData():StateFlow<RickMortyResource> {
        val stateFlow = MutableStateFlow<RickMortyResource>(RickMortyResource.Loading)
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                val flow = rickMortyRepository.getAllRickMortyData()
                flow.catch {
                    stateFlow.emit(RickMortyResource.Error(it.message?:"Error"))
                }
                    .collect{
                        if(it.isSuccessful){
                            val body = it.body()
                            val result = ArrayList(body?.results?: emptyList())
                            val list = ArrayList<RickMortyEntity>()
                            result.forEach { result->
                                list.add(
                                    RickMortyEntity(
                                        gender = result.gender,
                                        id = result.id,
                                        image = result.image,
                                        location = result.location.name,
                                        name = result.name,
                                        origin = result.origin.name,
                                        species = result.species,
                                        status = result.status,
                                        type = result.type,
                                        url = result.url,
                                        episode = result.episode[0]
                                    )
                                )
                            }
                            rickMortyRepository.addAllRickMortyData(list)
                            stateFlow.emit(RickMortyResource.Success(list))
                        }else{
                            stateFlow.emit(RickMortyResource.Error(it.errorBody().toString()))
                        }
                    }

            }else{
                rickMortyRepository.getDbAllRickMortyData()
                    .collect{
                        if(it.isNotEmpty()){
                            stateFlow.emit(RickMortyResource.Success(it))
                        }else{
                            stateFlow.emit(RickMortyResource.Error("No Internet Connection!!"))
                        }
                    }
            }
        }


        return stateFlow
    }
}

package com.example.paging3therickandmortyapi.viewmodel

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3therickandmortyapi.model.Result
import com.example.paging3therickandmortyapi.repository.RickMortyRepository
import com.example.paging3therickandmortyapi.retrofit.ApiClient
import kotlinx.coroutines.flow.catch

class PagingDataSource:PagingSource<Int,Result>() {

    private val TAG = "PagingDataSource"

    private val rickMortyRepository = RickMortyRepository(
        ApiClient.apiService
//        AppDatabase.getInstance(context).rickMortyDao()
    )

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
       try {
           var loadResult:LoadResult.Page<Int,Result>?=null
           val nextPage:Int = params.key?:1
           val response = rickMortyRepository.getAllRickMortyDataPaging(nextPage)

           var nextPageNumber:Int?=null
           var prevPageNumber:Int?=null
            response.catch {
                Log.d(TAG, "load: Catch")
            }.collect{
                if(it.info.next!=null){
                    val uriNext = Uri.parse(it.info.next)
                    val nextPageQuery = uriNext.getQueryParameter("page")
                    nextPageNumber = nextPageQuery?.toInt()

                    if(it.info.prev!=null){
                        val uriPrev = Uri.parse(it.info.prev as String?)
                        val prevPageQuery = uriPrev.getQueryParameter("page")
                        prevPageNumber = prevPageQuery?.toInt()
                    }

                    loadResult =  LoadResult.Page(
                        data= it.results,
                        prevKey = prevPageNumber,
                        nextKey = nextPageNumber
                    )


                }
            }


//
//            response.catch {
//                Log.d(TAG, "load: Catch prev")
//            }.collect{
//                if(it.info.prev!=null){
//                    val uri = Uri.parse(it.info.prev as String?)
//                    val prevPageQuery = uri.getQueryParameter("page")
//                    prevPageNumber = prevPageQuery?.toInt()
//                }
//            }

//           response.catch {
//
//            }.collect{
//
//              loadResult =  LoadResult.Page(
//                   data= it.results,
//                   prevKey = prevPageNumber,
//                   nextKey = nextPageNumber
//               )
//           }

           return loadResult!!

       }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }
}
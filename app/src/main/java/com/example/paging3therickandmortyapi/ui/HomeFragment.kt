package com.example.paging3therickandmortyapi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.paging3therickandmortyapi.R
import com.example.paging3therickandmortyapi.adapter.PagingRvAdapter
import com.example.paging3therickandmortyapi.databinding.FragmentHomeBinding
import com.example.paging3therickandmortyapi.viewmodel.RickMortyPagingModel
import com.example.paging3therickandmortyapi.viewmodel.ViewModelFactory
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
//    private lateinit var rickMortyAdapter: RickMortyAdapter
    private lateinit var rickMortyModel: RickMortyPagingModel
    private lateinit var pagingRvAdapter: PagingRvAdapter
    private lateinit var job:Job
    private val TAG = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        rickMortyModel = ViewModelProvider(
//            this,
//            ViewModelFactory(
//                NetworkHelper(requireContext()),
//                RickMortyRepository(
//                    ApiClient.apiService,
//                    AppDatabase.getInstance(requireContext()).rickMortyDao()
//                )
//            )
//        )[RickMortyModel::class.java]

        rickMortyModel = ViewModelProvider(
            this,
            ViewModelFactory()
        )[RickMortyPagingModel::class.java]

        pagingRvAdapter = PagingRvAdapter()
        binding.rv.adapter = pagingRvAdapter


//        rickMortyAdapter = RickMortyAdapter()
//        binding.rv.adapter = rickMortyAdapter


        lifecycleScope.launch{
            rickMortyModel.flowData().collectLatest{
                Log.d(TAG, "onViewCreated: RickMorty $it")
                pagingRvAdapter.submitData(it)
            }
        }



//        loadUI()
    }
//
//    private fun loadUI() {
//        lifecycleScope.launch {
//            rickMortyModel.getAllRickMortyData()
//                .collect{
//                    when(it){
//                        is RickMortyResource.Loading->{
//                            Log.d(TAG, "loadUI: Loading")
//                        }
//                        is RickMortyResource.Error->{
//                            Log.d(TAG, "loadUI: Error:${it.message}")
//                        }
//                        is RickMortyResource.Success->{
//                            Log.d(TAG, "loadUI: Success")
//                            Log.d(TAG, "loadUI: ${it.list?.get(0)?.name}")
//                            rickMortyAdapter.submitList(it.list)
//                        }
//                    }
//                }
//        }
//    }

}
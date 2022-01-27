package com.example.paging3therickandmortyapi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.paging3therickandmortyapi.R
import com.example.paging3therickandmortyapi.adapter.RickMortyAdapter
import com.example.paging3therickandmortyapi.database.AppDatabase
import com.example.paging3therickandmortyapi.databinding.FragmentHomeBinding
import com.example.paging3therickandmortyapi.repository.RickMortyRepository
import com.example.paging3therickandmortyapi.retrofit.ApiClient
import com.example.paging3therickandmortyapi.utils.NetworkHelper
import com.example.paging3therickandmortyapi.utils.RickMortyResource
import com.example.paging3therickandmortyapi.viewmodel.RickMortyModel
import com.example.paging3therickandmortyapi.viewmodel.ViewModelFactory
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var rickMortyAdapter: RickMortyAdapter
    private lateinit var rickMortyModel: RickMortyModel
    private val TAG = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rickMortyModel = ViewModelProvider(
            this,
            ViewModelFactory(
                NetworkHelper(requireContext()),
                RickMortyRepository(
                    ApiClient.apiService,
                    AppDatabase.getInstance(requireContext()).rickMortyDao()
                )
            )
        )[RickMortyModel::class.java]

        rickMortyAdapter = RickMortyAdapter()
        binding.rv.adapter = rickMortyAdapter
        loadUI()
    }

    private fun loadUI() {
        lifecycleScope.launch {
            rickMortyModel.getAllRickMortyData()
                .collect{
                    when(it){
                        is RickMortyResource.Loading->{
                            Log.d(TAG, "loadUI: Loading")
                        }
                        is RickMortyResource.Error->{
                            Log.d(TAG, "loadUI: Error:${it.message}")
                        }
                        is RickMortyResource.Success->{
                            Log.d(TAG, "loadUI: Success")
                            Log.d(TAG, "loadUI: ${it.list?.get(0)?.name}")
                            rickMortyAdapter.submitList(it.list)
                        }
                    }
                }
        }
    }

}
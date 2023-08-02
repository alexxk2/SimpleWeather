package com.example.simpleweather.presentation.search.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleweather.databinding.FragmentSearchBinding
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.presentation.search.ui.adapters.SearchAdapter
import com.example.simpleweather.presentation.search.view_model.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    viewModel.getCityLocation(name = binding.searchEditText.text.toString())
                    true
                }
                else -> false
            }
        }
        viewModel.cityInfo.observe(viewLifecycleOwner){
            searchAdapter.submitList(it)
        }

    }

    private fun setRecyclerView(){
        searchAdapter = SearchAdapter(requireContext(), object :SearchAdapter.SearchActionListener{

            override fun onClickItem(cityInfo: CityInfo) {

            }
        })

        binding.recyclerView.adapter = searchAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
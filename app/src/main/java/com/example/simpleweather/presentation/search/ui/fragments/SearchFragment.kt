package com.example.simpleweather.presentation.search.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleweather.databinding.FragmentSearchBinding
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.presentation.search.models.SearchStatus
import com.example.simpleweather.presentation.search.ui.adapters.SearchAdapter
import com.example.simpleweather.presentation.search.view_model.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        viewModel.getAllHistory()

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    viewModel.getCityLocation(name = binding.searchEditText.text.toString())
                    true
                }

                else -> false
            }
        }

        viewModel.cityInfo.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }

        viewModel.searchState.observe(viewLifecycleOwner) { searchState ->

            when (searchState) {
                SearchStatus.Done -> showContent()
                SearchStatus.Error -> showError()
                SearchStatus.Loading -> showLoading()
                SearchStatus.History -> showHistory()
                SearchStatus.Intro -> showIntro()
            }
        }

        binding.cleanHistoryButton.setOnClickListener {
            viewModel.clearHistory()
            viewModel.getAllHistory()
        }

        binding.searchLayout.setEndIconOnClickListener {
            binding.searchEditText.setText("")
            viewModel.getAllHistory()
        }


        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchDebounce(s = s)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setRecyclerView() {
        searchAdapter =
            SearchAdapter(requireContext(), object : SearchAdapter.SearchActionListener {

                override fun onClickItem(cityInfo: CityInfo) {
                    val action =
                        SearchFragmentDirections.actionSearchFragmentToInfoFragment(cityInfo)
                    findNavController().navigate(action)
                }
            })

        binding.recyclerView.adapter = searchAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun showContent() {
        with(binding) {
            notFoundLayout.visibility = View.GONE
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            historyTitle.visibility = View.GONE
            cleanHistoryButton.visibility = View.INVISIBLE
            introLayout.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            notFoundLayout.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            historyTitle.visibility = View.GONE
            cleanHistoryButton.visibility = View.INVISIBLE
            introLayout.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with(binding) {
            notFoundLayout.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            historyTitle.visibility = View.GONE
            cleanHistoryButton.visibility = View.INVISIBLE
            introLayout.visibility = View.GONE
        }
    }

    private fun showHistory() {
        with(binding) {
            notFoundLayout.visibility = View.GONE
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            historyTitle.visibility = View.VISIBLE
            cleanHistoryButton.visibility = View.VISIBLE
            introLayout.visibility = View.GONE
        }
    }

    private fun showIntro() {
        with(binding) {
            notFoundLayout.visibility = View.GONE
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            historyTitle.visibility = View.GONE
            cleanHistoryButton.visibility = View.INVISIBLE
            introLayout.visibility = View.VISIBLE

        }
    }

    private fun searchDebounce(s: CharSequence?) {
        if (!s.isNullOrEmpty()) {
            showLoading()
            val searchInput = s.toString()
            searchJob?.cancel()

            searchJob = viewLifecycleOwner.lifecycleScope.launch {
                delay(SEARCH_DEBOUNCE_DELAY)
                viewModel.getCityLocation(searchInput)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}
package com.example.simpleweather.presentation.info.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.simpleweather.R
import com.example.simpleweather.databinding.FragmentInfoBinding
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.presentation.info.view_model.InfoViewModel
import com.example.simpleweather.presentation.search.models.SearchStatus
import org.koin.androidx.viewmodel.ext.android.viewModel


class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InfoViewModel by viewModel()
    private lateinit var cityInfo: CityInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(CITY_INFO, CityInfo::class.java)!!
            } else it.getParcelable(CITY_INFO)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWeatherInfo(lat = cityInfo.lat, lon = cityInfo.lon)

        viewModel.weatherInfo.observe(viewLifecycleOwner) { weatherInfo ->

            with(binding) {
                cityName.text = cityInfo.name
                temperature.text = getString(R.string.temperature, weatherInfo.temp.toString())
                temperatureFeelsLike.text =
                    getString(R.string.temperature_feels_like, weatherInfo.feels_like.toString())
                pressure.text = getString(R.string.pressure, weatherInfo.pressure.toString())
                humidity.text = getString(R.string.humidity, weatherInfo.humidity.toString())
                windSpeed.text = getString(R.string.wind_speed, weatherInfo.wind_speed.toString())
                Glide.with(requireContext())
                    .load(weatherInfo.weatherImage)
                    .placeholder(R.drawable.ic_weather_placeholder)
                    .into(weatherImage)

                Glide.with(requireContext())
                    .load(cityInfo.flagImageSrc)
                    .placeholder(R.drawable.ic_flag_not_found)
                    .into(flagImage)

            }

        }

        viewModel.searchState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SearchStatus.Done -> showContent()
                SearchStatus.Error -> showError()
                SearchStatus.Loading -> showLoading()
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun showContent() {
        with(binding) {
            notFoundLayout.visibility = View.GONE
            progressBar.visibility = View.GONE
            showContentViews()
        }
    }

    private fun showError() {
        with(binding) {
            notFoundLayout.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            hideContentViews()
        }
    }

    private fun showLoading() {
        with(binding) {
            notFoundLayout.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            hideContentViews()
        }
    }

    private fun showContentViews() {
        with(binding) {
            cityName.visibility = View.VISIBLE
            temperature.visibility = View.VISIBLE
            temperatureFeelsLike.visibility = View.VISIBLE
            pressure.visibility = View.VISIBLE
            humidity.visibility = View.VISIBLE
            windSpeed.visibility = View.VISIBLE
            weatherImage.visibility = View.VISIBLE
            flagImage.visibility = View.VISIBLE
        }
    }

    private fun hideContentViews() {
        with(binding) {
            cityName.visibility = View.GONE
            temperature.visibility = View.GONE
            temperatureFeelsLike.visibility = View.GONE
            pressure.visibility = View.GONE
            humidity.visibility = View.GONE
            windSpeed.visibility = View.GONE
            weatherImage.visibility = View.GONE
            flagImage.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CITY_INFO = "cityInfo"
    }
}
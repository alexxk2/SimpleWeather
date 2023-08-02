package com.example.simpleweather.presentation.search.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleweather.R
import com.example.simpleweather.databinding.SearchItemBinding
import com.example.simpleweather.domain.models.CityInfo

class SearchAdapter(
    private val context: Context,
    private val actionListener: SearchActionListener
) :
    ListAdapter<CityInfo, SearchAdapter.SearchViewHolder>(DiffCallBack), View.OnClickListener {


    class SearchViewHolder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.cityName.setOnClickListener(this)

        return SearchViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        val cityInfoPrepared = context.getString(R.string.city_info, item.name, item.country)

        Glide.with(context)
            .load("https://raw.githubusercontent.com/alexxk2/CurrencyConverter/e2e52160395ee9aeae238b6c765073771db358fe/app/src/main/res/drawable/au.png")
            .into(holder.binding.flagImage)

        with(holder.binding) {
            cityName.text = cityInfoPrepared

        }
    }


    override fun onClick(v: View?) {
        val cityInfo = v?.tag as CityInfo
        when (v.id) {
            R.id.city_name -> actionListener.onClickItem(cityInfo)
            else -> actionListener.onClickItem(cityInfo)
        }
    }

    interface SearchActionListener {
        fun onClickItem(cityInfo: CityInfo)
    }


    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<CityInfo>() {

            override fun areItemsTheSame(oldItem: CityInfo, newItem: CityInfo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: CityInfo, newItem: CityInfo): Boolean {
                return oldItem == newItem
            }
        }
    }

}
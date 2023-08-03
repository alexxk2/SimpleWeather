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
import java.text.SimpleDateFormat
import java.util.*

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

        val simpleDate = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault())

        val cityInfoPrepared = if (item.dt == null){
             context.getString(R.string.city_info, item.name, item.country)
        }
        else{
             context.getString(R.string.city_info_plus_date, item.name, item.country, simpleDate.format(Date(item.dt*1000L)))
        }

        Glide.with(context)
            .load(item.flagImageSrc)
            .placeholder(R.drawable.ic_flag_not_found)
            .into(holder.binding.flagImage)

        with(holder.binding) {
            cityName.text = cityInfoPrepared
            cityName.tag = item
            root.tag = item
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
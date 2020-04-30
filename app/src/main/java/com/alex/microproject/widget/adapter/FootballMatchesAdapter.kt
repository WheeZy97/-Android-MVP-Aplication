package com.alex.microproject.widget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.microproject.R
import com.alex.microproject.model.realm.FootballMatch
import com.alex.microproject.util.MicroImage
import io.realm.OrderedRealmCollection
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_football_match_item.*

class FootballMatchesAdapter(private var itemList: OrderedRealmCollection<FootballMatch>, private val mOnItemListener: OnItemListener)
    : RecyclerView.Adapter<FootballMatchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_football_match_item, parent, false), mOnItemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    class ViewHolder(override val containerView: View, private val onItemListener: OnItemListener) : RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener {

        fun bind(footballMatch: FootballMatch) {
            match_title.text = footballMatch.title
            MicroImage.loadImageFromUrl(containerView.context, footballMatch.thumbnail, match_image)

            containerView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemListener.onItemClick(adapterPosition)
        }
    }

    interface OnItemListener {
        fun onItemClick(adapterPosition: Int)
    }
}
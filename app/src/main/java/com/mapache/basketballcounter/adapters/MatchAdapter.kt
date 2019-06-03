package com.mapache.basketballcounter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mapache.basketballcounter.R
import com.mapache.basketballcounter.database.entities.Match
import kotlinx.android.synthetic.main.list_item_match.view.*

class MatchAdapter(val clickListener: (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    private var matchs = emptyArray<Match>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_match, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = matchs[position]
        holder.bind(current, clickListener)
    }

    internal fun setMatchs(matchs : List<Match>){
        this.matchs = matchs.toTypedArray()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(item : Match, clickListener: (Match) -> Unit) = with(itemView){
            teamVs.text = item.teamA + " vs " + item.teamB
            list_date.text = item.date
            list_time.text = item.time
            this.setOnClickListener {clickListener(item)}
        }
    }
}
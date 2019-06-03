package com.mapache.basketballcounter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mapache.basketballcounter.R
import com.mapache.basketballcounter.database.entities.Match
import com.mapache.basketballcounter.database.viewmodels.MatchViewModel
import com.mapache.basketballcounter.utilities.AppConstants
import kotlinx.android.synthetic.main.fragment_content.view.*

class ContentFragment : Fragment() {

    lateinit var match : Match
    lateinit var matchViewModel : MatchViewModel

    companion object{
        fun newInstance(match : Match) : ContentFragment{
            val newFragment = ContentFragment()
            newFragment.match = match
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_content, container, false)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        if(savedInstanceState != null) match = savedInstanceState.getParcelable(AppConstants.MATCH_KEY)
        bindData(view)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(AppConstants.MATCH_KEY, match)
    }

    fun bindData(view: View){
        view.winner_tv.text = match.winner
        view.teamA_tv.text = match.teamA
        view.teamB_tv.text = match.teamB
        view.scoreA_tv.text = match.scoreA.toString()
        view.scoreB_tv.text = match.scoreB.toString()
        view.dia_tv.text = match.date
        view.hora_tv.text = match.time
    }
}
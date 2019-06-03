package com.mapache.basketballcounter.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mapache.basketballcounter.R
import com.mapache.basketballcounter.adapters.MatchAdapter
import com.mapache.basketballcounter.database.entities.Match
import com.mapache.basketballcounter.database.viewmodels.MatchViewModel
import com.mapache.basketballcounter.utilities.AppConstants
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    lateinit var matchViewModel : MatchViewModel
    lateinit var matchAdapter: MatchAdapter
    var flag : Boolean? = null
    var click : OnClickMatchListener? = null

    companion object{
        fun newInstance(flag : Boolean) : ListFragment{
            var newFragment = ListFragment()
            newFragment.flag = flag
            return newFragment
        }
    }

    interface OnClickMatchListener{
        fun OnClickSmallMatchListener(match : Match)
        fun OnClickBigMatchListener(match : Match)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnClickMatchListener) click = context
        else throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        if(savedInstanceState != null) flag = savedInstanceState.getBoolean(AppConstants.FLAG_KEY)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        initRecycler(view)
        return view
    }

    fun initRecycler(view: View){
        val linearLayoutManager = LinearLayoutManager(this.context)
        matchAdapter = if(this!!.flag!!) MatchAdapter({ match : Match -> click?.OnClickBigMatchListener(match)})
        else MatchAdapter({match : Match -> click?.OnClickSmallMatchListener(match)})
        view.recycler_view.adapter = matchAdapter
        matchViewModel.allMatch.observe(this, Observer { matchs ->
            matchs.let {matchAdapter.setMatchs(it)}
        })
        view.recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(AppConstants.FLAG_KEY, this!!.flag!!)
    }

    override fun onDetach() {
        super.onDetach()
        click = null
    }

}
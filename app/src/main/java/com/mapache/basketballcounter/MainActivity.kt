package com.mapache.basketballcounter

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.mapache.basketballcounter.database.entities.Match
import com.mapache.basketballcounter.fragments.ContentFragment
import com.mapache.basketballcounter.fragments.ListFragment
import com.mapache.basketballcounter.utilities.AppConstants

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), ListFragment.OnClickMatchListener {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, NewMatchActivity::class.java))
        }

        var resource = 0
        if (list_big_fragment != null){
            resource = R.id.list_big_fragment
            listFragment = ListFragment.newInstance(true)
        }
        else {
            resource = R.id.list_small_fragmnet
            listFragment = ListFragment.newInstance(false)
        }
        changeFragment(resource, listFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun OnClickSmallMatchListener(match : Match) {
        var mIntent = Intent(this, MatchActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(AppConstants.MATCH_KEY, match)
        mIntent.putExtras(bundle)
        startActivity(mIntent)
    }

    override fun OnClickBigMatchListener(match : Match) {
        var contentFragment = ContentFragment.newInstance(match)
        changeFragment(R.id.content_fragment, contentFragment)
    }
}

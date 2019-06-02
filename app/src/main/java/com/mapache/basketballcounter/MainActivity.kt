package com.mapache.basketballcounter

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.mapache.basketballcounter.database.entities.Match
import com.mapache.basketballcounter.fragments.ListFragment
import com.mapache.basketballcounter.utilities.AppConstants

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.OnClickMatchListener {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->

        }

        listFragment = ListFragment.newInstance()
        var resource = if (resources.configuration.smallestScreenWidthDp >= 672) R.id.list_big_fragment
        else R.id.list_small_fragmnet
        changeFragment(resource, listFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun OnClickSmallMatchListener(match : Match) {

    }

    override fun OnClickBigMatchListener(match : Match) {
        var mIntent = Intent(this, MatchActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(AppConstants.MATCH_KEY, match)
        mIntent.putExtras(bundle)
        startActivity(mIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

package com.mapache.basketballcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mapache.basketballcounter.database.entities.Match
import com.mapache.basketballcounter.fragments.ContentFragment
import com.mapache.basketballcounter.utilities.AppConstants

class MatchActivity : AppCompatActivity() {

    lateinit var contentFragment: ContentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val match : Match = intent?.extras?.getParcelable(AppConstants.MATCH_KEY)!!
        contentFragment = ContentFragment.newInstance(match)
        changeFragment(R.id.activity_match_fragment, contentFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

}

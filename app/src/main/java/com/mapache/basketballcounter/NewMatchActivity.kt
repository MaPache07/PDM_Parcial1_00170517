package com.mapache.basketballcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mapache.basketballcounter.database.entities.Match
import com.mapache.basketballcounter.database.viewmodels.MatchViewModel
import com.mapache.basketballcounter.databinding.ActivityNewMatchBinding
import com.mapache.basketballcounter.pojos.Score
import kotlinx.android.synthetic.main.activity_new_match.*
import kotlinx.android.synthetic.main.fragment_content.*

class NewMatchActivity : AppCompatActivity() {

    lateinit var matchViewModel : MatchViewModel
    lateinit var score : Score

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_match)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        score = ViewModelProviders.of(this).get(Score::class.java)

        if(score.scoreA.get() == null) score.scoreA.set("0")
        if(score.scoreB.get() == null) score.scoreB.set("0")

        var binding : ActivityNewMatchBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_match)
        binding.score = score

        action_save.setOnClickListener {
            if(teamA_et.text.isEmpty() || teamB_et.text.isEmpty() ||
                date_et.text.isEmpty() || time_et.text.isEmpty() ||
                (score.scoreA.get() == "0" && score.scoreB.get() == "0")){
                Snackbar.make(it, "Enter all data", Snackbar.LENGTH_LONG).show()
            } else{
                var winner = ""
                if(score.scoreA.get() != score.scoreB.get()) {
                    if(score.scoreA.get()!!.toInt() >  score.scoreB.get()!!.toInt()) winner = teamA_et.text.toString()
                    else winner = teamB_et.text.toString()
                }
                else winner = "Tie"
                var match = Match(0, teamA_et.text.toString(), teamB_et.text.toString(), score.scoreA.get()!!.toInt(),
                    score.scoreB.get()!!.toInt(), winner, date_et.text.toString(), time_et.text.toString())
                matchViewModel.insert(match)
                finish()
            }
        }
    }

    fun scorePlus(view : View){
        when(view.id){
            R.id.teamA_1 -> score.scoreA.set((score.scoreA.get()!!.toInt()+1).toString())
            R.id.teamA_2 -> score.scoreA.set((score.scoreA.get()!!.toInt()+2).toString())
            R.id.teamA_3 -> score.scoreA.set((score.scoreA.get()!!.toInt()+3).toString())
            R.id.teamB_1 -> score.scoreB.set((score.scoreB.get()!!.toInt()+1).toString())
            R.id.teamB_2 -> score.scoreB.set((score.scoreB.get()!!.toInt()+2).toString())
            R.id.teamB_3 -> score.scoreB.set((score.scoreB.get()!!.toInt()+3).toString())
        }
    }
}

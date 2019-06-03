package com.mapache.basketballcounter.pojos

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class Score : ViewModel() {
    var scoreA = ObservableField<String>()
    var scoreB = ObservableField<String>()
}
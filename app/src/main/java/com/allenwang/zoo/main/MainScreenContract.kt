package com.allenwang.zoo.main

import com.allenwang.zoo.pojo.Park
import com.allenwang.zoo.pojo.Plant

interface MainScreenContract {

    interface StoryView {
        fun showError(msg: String)
        fun showParks(parks: List<Park>)
        fun showProgressBar(visible: Boolean)
    }
}
package com.allenwang.zoo.main

import com.allenwang.zoo.webservices.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainScreenPresenter {
    private val apiService by lazy {
        APIService.create()
    }

    private lateinit var view: MainScreenContract.StoryView
    var disposable =  CompositeDisposable()

    fun bind(view: MainScreenContract.StoryView) {
        this.view = view
    }

    fun unbind(){
        disposable.dispose()
    }

    fun getParks(){
        view.showProgressBar(true)
        disposable.add(
            apiService.getParks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { parkBase ->
                        view.showParks(parkBase.result.results)
                    },
                    { error ->
                        view.showError(error.message ?: "Error")
                    },
                    {
                        view.showProgressBar(false)
                    }
                )
        )
    }


}
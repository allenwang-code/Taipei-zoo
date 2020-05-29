package com.allenwang.zoo.main

import com.allenwang.zoo.pojo.Park
import com.allenwang.zoo.pojo.ParkBase
import com.allenwang.zoo.pojo.Plant
import com.allenwang.zoo.pojo.PlantBase
import com.allenwang.zoo.webservices.APIService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MainScreenPresenter {
    private val apiService by lazy {
        APIService.create()
    }

    private lateinit var view: MainScreenContract.StoryView
    var disposable = CompositeDisposable()

    fun bind(view: MainScreenContract.StoryView) {
        this.view = view
    }

    fun unbind() {
        disposable.dispose()
    }

    fun getParks() {
        view.showProgressBar(true)

        val requests: MutableList<Observable<*>> = ArrayList()
        requests.add(apiService.getParks())
        requests.add(apiService.getPlants())

        val d =
            Observable.zip(
                apiService.getParks(),
                apiService.getPlants(),
                BiFunction<ParkBase, PlantBase, List<Park>> { parkBase, plantBase ->
                    val parks = insertPlantIntoPark(parkBase, plantBase)
                    parks
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { parks ->
                        view.showParks(parks)
                    },
                    { error ->
                        view.showError(error.message ?: "Error")
                    },
                    {
                        view.showProgressBar(false)
                    }
                )

        disposable.add(d)
    }

    private fun insertPlantIntoPark(
        parkBase: ParkBase,
        plantBase: PlantBase
    ): List<Park> {
        val parks = parkBase.result.results
        val plants = plantBase.result.results
        parks.forEach { park ->
            val insertedPlants = ArrayList<Plant>()
            plants.forEach { plant ->
                if (plant.F_Location.contains(park.eName)) {
                    insertedPlants.add(plant)
                }
            }
            park.plants = insertedPlants
        }
        return parks
    }


}
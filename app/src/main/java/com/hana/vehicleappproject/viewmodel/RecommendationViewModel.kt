package com.hana.vehicleappproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hana.vehicleappproject.data.dummy.RecommendationDummyData
import com.hana.vehicleappproject.data.dummy.Recommendation

class RecommendationViewModel : ViewModel() {
    private val _recommendations = MutableLiveData<List<Recommendation>>()
    val recommendations: LiveData<List<Recommendation>> get() = _recommendations

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        _recommendations.value = RecommendationDummyData.recommendations
    }
}

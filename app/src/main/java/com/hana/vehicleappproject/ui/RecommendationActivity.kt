package com.hana.vehicleappproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.adapter.RecommendationAdapter
import com.hana.vehicleappproject.viewmodel.RecommendationViewModel

class RecommendationActivity : AppCompatActivity() {
    private lateinit var viewModel: RecommendationViewModel
    private lateinit var adapter: RecommendationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation)

        viewModel = ViewModelProvider(this).get(RecommendationViewModel::class.java)
        adapter = RecommendationAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.recommendations.observe(this) { recommendations ->
            adapter.submitList(recommendations)
        }
    }
}

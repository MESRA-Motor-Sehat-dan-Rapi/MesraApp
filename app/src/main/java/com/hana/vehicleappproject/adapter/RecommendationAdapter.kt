package com.hana.vehicleappproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.data.dummy.Recommendation

class RecommendationAdapter : RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    private val recommendations = mutableListOf<Recommendation>()

    fun submitList(newRecommendations: List<Recommendation>) {
        recommendations.clear()
        recommendations.addAll(newRecommendations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recommendation = recommendations[position]
        holder.bind(recommendation)
    }

    override fun getItemCount(): Int = recommendations.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)

        fun bind(recommendation: Recommendation) {
            titleTextView.text = recommendation.title
            descriptionTextView.text = recommendation.description
            categoryTextView.text = recommendation.category
        }
    }
}

package com.example.yapeapp.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.yapeapp.helpers.load
import com.example.yapeapp.R
import com.example.yapeapp.data.model.Recipe

/**
 * @author Axel Sanchez
 */
class RecipeAdapter(
    private var recipes: List<Recipe?>,
    private val itemClick: (Recipe?) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recipes[position]

        item?.let { recipe ->

            with(holder){
                name.text = recipe.name
                imageView.load(recipe.image)
                itemView.setOnClickListener { itemClick(recipe) }
            }
        }
    }

    override fun getItemCount(): Int = recipes.size
    fun updateRecipes(filteredList: List<Recipe?>) {
        recipes = filteredList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivItemImage)
        val name: TextView = view.findViewById(R.id.tvName)
    }
}
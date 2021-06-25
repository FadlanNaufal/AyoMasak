package com.fadlandev.ayomasak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.fadlandev.ayomasak.R
import com.fadlandev.ayomasak.model.Recipe
import com.fadlandev.ayomasak.response.RecipeResponse
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(private val list: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

    private var itemClickCallback: OnItemClickCallback? = null

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.itemClickCallback = onItemClickCallback
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(recipe: Recipe){
            with(itemView){
                val title = recipe.recipe_title
                val desc = recipe.recipe_desc
                val portion = recipe.recipe_portion
                val time = recipe.recipe_time
                val image = "http://192.168.100.17/AyoMasak/public/storage/${recipe.recipe_image}"

                tv_title.text = title
                tv_desc.text = desc
                tv_portion.text = "${portion} serve"
                tv_time.text = "${time} Minute"

                Glide.with(this)
                    .load(image)
                    .transform(CenterCrop(),RoundedCorners(20))
                    .error(R.drawable.dummy)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(cv_image)

                itemView.setOnClickListener {
                    itemClickCallback?.onItemClicked(recipe)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_recipe,parent,false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Recipe)
    }
}
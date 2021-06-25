package com.fadlandev.ayomasak.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadlandev.ayomasak.R
import com.fadlandev.ayomasak.adapter.RecipeAdapter
import com.fadlandev.ayomasak.api.RetrofitClient
import com.fadlandev.ayomasak.model.Recipe
import com.fadlandev.ayomasak.response.RecipeResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

    }

    private fun getData() {
        rv_recipe.setHasFixedSize(true)
        rv_recipe.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getRecipe().enqueue(object: Callback<RecipeResponse>{
            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Gagal", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                val listRecipeResponse = response.body()?.data
                listRecipeResponse?.let { list.addAll(it) }
                val adapter = RecipeAdapter(list)
                rv_recipe.adapter = adapter

                adapter.setOnClickCallback(object: RecipeAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: Recipe) {
                        var intent = Intent(this@MainActivity,DetailActivity::class.java)
                        intent.putExtra("title",data.recipe_title)
                        intent.putExtra("desc",data.recipe_desc)
                        intent.putExtra("ingredient",data.recipe_ingredient)
                        intent.putExtra("image",data.recipe_image)
                        intent.putExtra("portion",data.recipe_portion)
                        intent.putExtra("time",data.recipe_time)
                        startActivity(intent)
                        Toast.makeText(this@MainActivity,data.recipe_title, Toast.LENGTH_LONG).show()
                    }

                })
                Toast.makeText(this@MainActivity,"Berhasil", Toast.LENGTH_LONG).show()
            }

        })
    }
}

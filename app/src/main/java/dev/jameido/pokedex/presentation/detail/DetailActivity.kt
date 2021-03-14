package dev.jameido.pokedex.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.jameido.pokedex.R
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Jameido on 04/01/2021.
 */
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            intent.extras?.getString(KEY_NAME)?.let {
                openDetail(it)
            }
        }
    }

    private fun openDetail(name: String) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_detail, DetailFragment.newInstance(name))
                .commit()
    }

    companion object {
        private const val KEY_NAME = "NAME"

        fun getStartIntent(context: Context, name: String) =
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(KEY_NAME, name)
                }
    }
}
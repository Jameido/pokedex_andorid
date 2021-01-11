package dev.jameido.pokedex.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dev.jameido.pokedex.presentation.list.ListActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Jameido on 11/01/2021.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch { openListActivity() }
    }

    private suspend fun openListActivity() {
        delay(300)
        startActivity(ListActivity.getStartIntent(this))
        finish()
    }
}
package dev.jameido.pokedex.presentation.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.jameido.pokedex.R
import dev.jameido.pokedex.presentation.detail.DetailActivity
import dev.jameido.pokedex.presentation.detail.DetailFragment
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListActivity : AppCompatActivity() {

    private val viewModel: PkmnListVM by viewModel()
    private lateinit var adapter: PkmnAdapter
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        twoPane = findViewById<View>(R.id.container_detail) != null

        adapter = PkmnAdapter { name -> openDetail(name) }
        rv_pkmn.adapter = adapter.withLoadStateLoaderHeaderFooter(
                loader = PkmnLoadStateAdapter(adapter::retry),
                header = PkmnLoadStateAdapter(adapter::retry),
                footer = PkmnLoadStateAdapter(adapter::retry)
        )

        rv_pkmn.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        lifecycleScope.launch {
            viewModel.list.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun openDetail(name: String) {
        if (twoPane) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_detail, DetailFragment.newInstance(name))
                    .commit()
        } else {
            startActivity(DetailActivity.getStartIntent(this, name))
        }
    }
}
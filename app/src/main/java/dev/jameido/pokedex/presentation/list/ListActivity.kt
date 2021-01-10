package dev.jameido.pokedex.presentation.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.jameido.pokedex.R
import dev.jameido.pokedex.presentation.detail.DetailActivity
import dev.jameido.pokedex.presentation.detail.DetailFragment
import io.uniflow.androidx.flow.onEvents
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val viewModel: PkmnListVM by viewModel()
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        twoPane = findViewById<View>(R.id.container_detail) != null

        val adapter = PkmnAdapter { name -> openDetail(name) }

        configRecyclerView(adapter)
        configSearchView()

        container_list_error.findViewById<View>(R.id.btn_retry).setOnClickListener {
            adapter.refresh()
        }

        lifecycleScope.launch {
            viewModel.list.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        img_list_loading.visibility = View.VISIBLE
                        container_list_error.visibility = View.INVISIBLE
                    }
                    is LoadState.Error -> {
                        img_list_loading.visibility = View.INVISIBLE
                        container_list_error.visibility = View.VISIBLE
                        rv_pkmn.visibility = View.INVISIBLE
                    }
                    is LoadState.NotLoading -> {
                        img_list_loading.visibility = View.INVISIBLE
                        container_list_error.visibility = View.INVISIBLE
                        rv_pkmn.visibility = View.VISIBLE
                    }
                }
            }
        }

        onEvents(viewModel) { event ->
            when (event.take()) {
                is PkmnListEvents.QueryChanged -> adapter.refresh()
            }

        }
    }

    private fun configRecyclerView(adapter: PkmnAdapter) {

        rv_pkmn.adapter = adapter.withLoadStateLoaderHeaderFooter(
                loader = PkmnLoadStateAdapter(adapter::retry),
                header = PkmnLoadStateAdapter(adapter::retry),
                footer = PkmnLoadStateAdapter(adapter::retry)
        )

        rv_pkmn.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

    }

    private fun configSearchView() {


        search_list.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.applyFilter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.applyFilter(newText)
                }
                return false
            }
        })
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
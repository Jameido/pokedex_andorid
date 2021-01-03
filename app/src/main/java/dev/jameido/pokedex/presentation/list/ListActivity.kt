package dev.jameido.pokedex.presentation.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.jameido.pokedex.R
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListActivity : AppCompatActivity() {

    private val viewModel: PkmnListVM by viewModel()
    private lateinit var adapter: PkmnAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        adapter = PkmnAdapter()
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
}
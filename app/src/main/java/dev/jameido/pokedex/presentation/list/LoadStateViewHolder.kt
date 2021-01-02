package dev.jameido.pokedex.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import dev.jameido.pokedex.R

/**
 * Created by Jameido on 02/01/2021.
 */
class LoadStateViewHolder(parent: ViewGroup, retry: () -> Unit) :
        RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_load, parent, false)
        ) {
    private val progressBar: ProgressBar = itemView.findViewById(R.id.prg_loading)
    private val txtError: AppCompatTextView = itemView.findViewById(R.id.txt_error)
    private val btnRetry = itemView.findViewById<AppCompatButton>(R.id.btn_retry)
            .also {
                it.setOnClickListener { retry() }
            }

    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                txtError.visibility = View.VISIBLE
                btnRetry.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
            is LoadState.Loading -> {
                txtError.visibility = View.GONE
                btnRetry.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        }
    }
}
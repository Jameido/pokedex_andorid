package dev.jameido.pokedex.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.jameido.pokedex.R
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIState
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Created by Jameido on 03/01/2021.
 */
class DetailFragment : BottomSheetDialogFragment() {

    private val viewModel: PkmnDetailVM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        onStates(viewModel) { state ->
            when (state) {
                is UIState.Loading -> onDataLoading()
                is UIState.Failed -> onDataError()
                is PkmnDetailState -> onDataLoaded(state.detail)
            }
        }
        loadData()
        return inflater.inflate(R.layout.content_detail, container,
                false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_detail_data_retry.setOnClickListener { loadData() }
    }

    private fun loadData() {
        viewModel.loadDetail(arguments?.getString(KEY_NAME, "") ?: "")
    }

    private fun onDataLoading() {
        container_detail_top.visibility = View.VISIBLE
        container_detail_data_error.visibility = View.INVISIBLE
        shimmer_detail_data.showShimmer(true)
    }

    private fun onDataError() {
        shimmer_detail_data.hideShimmer()
        container_detail_top.visibility = View.INVISIBLE
        container_detail_data_error.visibility = View.VISIBLE
    }

    private fun onDataLoaded(pkmn: PkmnDetailEntity) {
        shimmer_detail_data.hideShimmer()
        Glide.with(img_detail_sprite)
                .load(pkmn.sprite)
                .placeholder(R.drawable.missingno)
                .error(R.drawable.missingno)
                .into(img_detail_sprite)
        txt_detail_name.text = pkmn.name.capitalize(Locale.getDefault())
        txt_detail_nr.text = getString(R.string.nr, pkmn.id)
        txt_detail_weight.text = pkmn.weight.toString()
        txt_detail_height.text = pkmn.height.toString()

        displayType(pkmn.types.getOrNull(0), txt_detail_first_type)
        displayType(pkmn.types.getOrNull(1), txt_detail_second_type)
    }

    private fun displayType(type: String?, txtView: AppCompatTextView) {
        type?.let {
            txtView.text = it
            txtView.visibility = View.VISIBLE
        } ?: run {
            txtView.text = ""
            txtView.visibility = View.INVISIBLE
        }
    }

    companion object {
        const val TAG = "DetailFragment"
        private const val KEY_NAME = "NAME"

        fun newInstance(name: String): DetailFragment {
            val instance = DetailFragment()
            instance.arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
            return instance
        }
    }
}
package dev.jameido.pokedex.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jameido.pokedex.R
import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity
import dev.jameido.pokedex.views.ErrorViewUtil
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Created by Jameido on 03/01/2021.
 */
class DetailFragment : Fragment() {

    private val varietyVM: PkmnVarietyVM by viewModel()
    private val speciesVM: PkmnSpeciesVM by viewModel()

    private val varietyAdapter = VarietyAdapter { name -> varietyVM.load(name) }
    private val statAdapter = StatAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        onStates(speciesVM) { state ->
            when (state) {
                is PkmnSpeciesStates.Loading -> onSpeciesLoading()
                is PkmnSpeciesStates.Error -> onSpeciesError(state.error, state.name)
                is PkmnSpeciesStates.Loaded -> onSpeciesLoaded(state.species)
            }
        }

        onEvents(speciesVM) { event ->
            when (val data = event.take()) {
                is PkmnSpeciesEvents.SpeciesLoaded -> varietyVM.load(data.defaultVariety)
            }

        }

        onStates(varietyVM) { state ->
            when (state) {
                is PkmnVarietyStates.Loading -> onVarietyLoading()
                is PkmnVarietyStates.Error -> onVarietyError(state.error, state.name)
                is PkmnVarietyStates.Loaded -> onVarietyLoaded(state.detail)
            }
        }

        return inflater.inflate(R.layout.content_detail, container,
                false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_varieties.adapter = varietyAdapter
        rv_varieties.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL))

        rv_stats.adapter = statAdapter

        if (savedInstanceState == null) {
            arguments?.getString(KEY_NAME, null)?.let {
                speciesVM.load(it)
            }
        }
    }

    private fun onVarietyLoading() {
        loadingDetailData()
        shimmer_detail_data.visibility = View.VISIBLE
        container_detail_data_error.visibility = View.INVISIBLE
        shimmer_detail_data.showShimmer(true)
    }

    private fun onVarietyError(error: Throwable, name: String) {
        shimmer_detail_data.hideShimmer()
        shimmer_detail_data.visibility = View.INVISIBLE
        container_detail_data_error.visibility = View.VISIBLE
        ErrorViewUtil.showErrorContent(error, container_detail_data_error.findViewById(R.id.txt_error), container_detail_data_error.findViewById(R.id.img_error))
        container_detail_data_error.findViewById<AppCompatButton>(R.id.btn_retry).setOnClickListener { varietyVM.load(name) }
    }

    private fun onVarietyLoaded(pkmn: PkmnDetailEntity) {
        shimmer_detail_data.hideShimmer()
        Glide.with(img_detail_sprite)
                .load(pkmn.sprite)
                .placeholder(R.drawable.ic_missingno)
                .error(R.drawable.ic_missingno)
                .into(img_detail_sprite)
        txt_detail_name.text = pkmn.name.capitalize(Locale.getDefault())
        txt_detail_nr.text = getString(R.string.nr, pkmn.id)
        txt_detail_weight.text = getString(R.string.weight_format, pkmn.weight)
        txt_detail_height.text = getString(R.string.height_format, pkmn.height)

        txt_detail_name.background = null
        txt_detail_nr.background = null
        txt_detail_weight.background = null
        txt_detail_height.background = null

        displayType(pkmn.types.getOrNull(0), txt_detail_first_type)
        displayType(pkmn.types.getOrNull(1), txt_detail_second_type)

        statAdapter.updateItems(pkmn.stats)

        scroll_detail.smoothScrollTo(0, 0)
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

    private fun loadingDetailData() {
        img_detail_sprite.setImageResource(R.color.img_loading_shimmer)

        txt_detail_name.text = ""
        txt_detail_nr.text = ""
        txt_detail_weight.text = ""
        txt_detail_height.text = ""
        displayType("", txt_detail_first_type)
        displayType("", txt_detail_second_type)

        statAdapter.updateItems(listOf(null, null, null, null, null, null))

        txt_detail_name.setBackgroundResource(R.color.img_loading_shimmer)
        txt_detail_nr.setBackgroundResource(R.color.img_loading_shimmer)
        txt_detail_weight.setBackgroundResource(R.color.img_loading_shimmer)
        txt_detail_height.setBackgroundResource(R.color.img_loading_shimmer)
    }

    private fun onSpeciesLoading() {
        shimmer_varieties.visibility = View.VISIBLE
        varietyAdapter.updateItems(listOf(null, null))
        container_varieties_error.visibility = View.INVISIBLE
        shimmer_varieties.showShimmer(true)
    }

    private fun onSpeciesError(error: Throwable, name: String) {
        container_varieties_error.findViewById<AppCompatButton>(R.id.btn_retry).setOnClickListener { speciesVM.load(name) }
        shimmer_varieties.hideShimmer()
        shimmer_varieties.visibility = View.INVISIBLE
        ErrorViewUtil.showErrorContent(error, container_varieties_error.findViewById(R.id.txt_error), container_varieties_error.findViewById(R.id.img_error))
        container_varieties_error.visibility = View.VISIBLE
    }

    private fun onSpeciesLoaded(species: PkmnSpeciesEntity) {
        shimmer_varieties.visibility = View.VISIBLE
        txt_detail_description.text = species.description
        varietyAdapter.updateItems(species.varieties)
        shimmer_varieties.hideShimmer()
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
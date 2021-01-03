package dev.jameido.pokedex.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.jameido.pokedex.R
import dev.jameido.pokedex.presentation.list.PkmnListVM
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Jameido on 03/01/2021.
 */
class DetailFragment : BottomSheetDialogFragment() {

    private val viewModel: PkmnDetailVM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // get the views and attach the listener
        return inflater.inflate(R.layout.content_detail, container,
                false)
    }

    companion object {
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
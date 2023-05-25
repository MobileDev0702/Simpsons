package com.sample.simpsonsviewer.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.databinding.FragmentDetailBinding
import com.sample.simpsonsviewer.entities.Response

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    companion object {
        const val ARG_ITEM = "arg_item"
    }

    private lateinit var binding: FragmentDetailBinding
    private var item: Response.CharacterItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = arguments?.getParcelable(ARG_ITEM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        showDetails()
        return binding.root
    }

    private fun showDetails() {
        item?.let {
            binding.tvTitle.text = it.firstUrl.substring(23)
            if (it.text.isBlank()) {
                binding.tvDescription.text = getString(R.string.no_description)
            } else {
                binding.tvDescription.text = Html.fromHtml(
                    it.text,
                    Html.FROM_HTML_MODE_LEGACY
                )
            }
        }
    }
}
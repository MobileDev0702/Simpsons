package com.sample.simpsonsviewer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.adapters.CharacterListAdapter
import com.sample.simpsonsviewer.databinding.FragmentMainBinding
import com.sample.simpsonsviewer.entities.LoadResult
import com.sample.simpsonsviewer.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    private var dataFetched: Boolean = false
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = CharacterListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvList.adapter = adapter
        adapter.setItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(DetailFragment.ARG_ITEM, it)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

        viewModel.characterList.observe(viewLifecycleOwner) {
            dataFetched = true
            adapter.submitList(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it == LoadResult.LOADING
            binding.rvList.isVisible = it == LoadResult.SUCCESS
        }

        if (savedInstanceState == null && !dataFetched) {
            viewModel.fetchCharacterResult()
        }
    }
}
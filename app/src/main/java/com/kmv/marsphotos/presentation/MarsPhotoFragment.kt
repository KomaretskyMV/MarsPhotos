package com.kmv.marsphotos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmv.marsphotos.R
import com.kmv.marsphotos.databinding.FragmentMarsPhotoBinding
import com.kmv.marsphotos.entity.Photo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

const val PHOTO = "photo"

@AndroidEntryPoint
class MarsPhotoFragment : Fragment() {

    private val viewModel: MarsPhotoPagedListViewModel by viewModels()

    private val pagedAdapter = MarsPhotoPagedListAdapter { photo -> onItemClick(photo) }

    private var _binding: FragmentMarsPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = pagedAdapter.withLoadStateFooter(MarsPhotoLoadStateAdapter())

        binding.swipeRefresh.setOnRefreshListener {
            pagedAdapter.refresh()
        }
        viewModel.pagedMarsPhoto.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        pagedAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onItemClick(item: Photo) {
        val bundle = bundleOf(PHOTO to item.img_src)
        findNavController().navigate(R.id.marsPhotoDetailsFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
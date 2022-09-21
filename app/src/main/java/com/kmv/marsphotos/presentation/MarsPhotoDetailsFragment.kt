package com.kmv.marsphotos.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kmv.marsphotos.R
import com.kmv.marsphotos.databinding.FragmentMarsPhotoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarsPhotoDetailsFragment : Fragment() {

    private var _binding: FragmentMarsPhotoDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.photo.context)
            .load(arguments?.getString(PHOTO))
            .into(binding.photo)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
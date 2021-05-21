package com.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ioscameraandroidapp.R
import com.example.ioscameraandroidapp.databinding.BeautyModeBinding
import com.ui.main.viewModel.ViewModelClass

class BeautyModeClass() : Fragment() {
    private lateinit var binding: BeautyModeBinding
    private val viewModelClass: ViewModelClass by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.beauty_mode, container, false
            )
        viewModelClass.beautyImages.observe(viewLifecycleOwner) { beautyImage ->

            Glide.with(requireActivity()).load(beautyImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.beautymodepreviewid)
        }

            return binding.root
    }

}
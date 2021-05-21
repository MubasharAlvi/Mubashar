package com.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ioscameraandroidapp.R
import com.example.ioscameraandroidapp.databinding.SquareFragmentLayoutBinding

class SquareFragment : Fragment() {
    lateinit var binding: SquareFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.square_fragment_layout, container, false
        )
        binding.cameraId.setOnClickListener {
            val action=
                SquareFragmentDirections.actionSquareFragment2ToCameraHomeScreenClass2()
            findNavController().navigate(action)
        }
        binding.videoid.setOnClickListener {
            val action=
                SquareFragmentDirections.actionSquareFragment2ToVideoFragment22()
            findNavController().navigate(action)
        }
        return binding.root
    }

}
package com.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ioscameraandroidapp.R
import com.example.ioscameraandroidapp.databinding.CameraHomeScreenBinding
import com.example.ioscameraandroidapp.databinding.VideoFragmentLayoutBinding
import com.ui.main.viewModel.ViewModelClass

class VideoFragment : Fragment() {

    var camera: Camera? = null
    var preview: Preview? = null
    var imageCapture: ImageCapture? = null
    val cameraProvider : ProcessCameraProvider? = null
    lateinit var binding : VideoFragmentLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                layoutInflater ,
        R.layout.video_fragment_layout , container, false)
binding.cameraId.setOnClickListener {
    val action=
        VideoFragmentDirections.actionVideoFragment2ToCameraHomeScreenClass2()
    findNavController().navigate(action)
}
        binding.squareid.setOnClickListener {
    val action=
        VideoFragmentDirections.actionVideoFragment2ToSquareFragment22()
    findNavController().navigate(action)
}
    return binding.root
    }

}
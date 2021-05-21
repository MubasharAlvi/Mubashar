package com.ui.main.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FLASH_MODE_OFF
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.core.impl.ImageCaptureConfig
import androidx.camera.core.impl.PreviewConfig
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.media.MediaBrowserServiceCompat.RESULT_OK
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ioscameraandroidapp.R
import com.example.ioscameraandroidapp.databinding.CameraHomeScreenBinding
import com.ui.main.viewModel.ViewModelClass
import io.fotoapparat.view.Preview
import kotlinx.android.synthetic.main.camera_home_screen.*

@Suppress("DEPRECATION")
class CameraHomeScreenFragment : Fragment() {
    var imageCapture: ImageCapture? = null
    private val REQUEST_IMAGE_CAPTURE = 1
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private val REQUEST_PICK_IMAGE = 2
    var flashMode: Int = FLASH_MODE_OFF
    lateinit var cameraProvider : CameraProvider
    lateinit var processCameraProvider: ProcessCameraProvider
    lateinit var binding: CameraHomeScreenBinding
    private val viewModelClass: ViewModelClass by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PERMISSION_GRANTED
        ) {
            viewModelClass.init(this, requireActivity())
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                110
            )
        }
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.camera_home_screen, container, false
        )
        viewModelClass.beautyImages.observe(viewLifecycleOwner) { beautyImage ->

            Glide.with(requireActivity()).load(beautyImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.beautymodeid)

        }
        binding.cameraid.setOnClickListener {
            viewModelClass.takePhoto()
        }
        binding.galleryIconId.setOnClickListener {
            gallery()
        }
        binding.flashoff.setOnClickListener {
            flashon.visibility = View.VISIBLE
            flashoff.visibility = View.GONE
            flashMode = FLASH_MODE_ON
            startCamera()
        }
        binding.flashon.setOnClickListener {
            flashoff.visibility = View.VISIBLE
            flashon.visibility = View.GONE
            flashMode = FLASH_MODE_OFF
            startCamera()
        }

        binding.switchLens.setOnClickListener {
            processCameraProvider.unbindAll()
            if (lensFacing == CameraSelector.LENS_FACING_BACK) {
                lensFacing = CameraSelector.LENS_FACING_FRONT
            } else {
                lensFacing = CameraSelector.LENS_FACING_BACK
            }
            startCamera()
        }
        binding.videoid.setOnClickListener {
            val action =
                CameraHomeScreenFragmentDirections.actionCameraHomeScreenClassToVideoFragment2()
            findNavController().navigate(action)
        }
        binding.squareid.setOnClickListener {
            val action =
                CameraHomeScreenFragmentDirections.actionCameraHomeScreenClassToSquareFragment2()
            findNavController().navigate(action)
        }
        binding.beautymodeid.setOnClickListener {
            val action =
                CameraHomeScreenFragmentDirections.actionCameraHomeScreenClassToBeautyModeClass2()
            findNavController().navigate(action)
        }
        return binding.root
    }

    private fun gallery() {
        Intent(Intent.ACTION_GET_CONTENT).also { intent ->
            intent.type = "image/*"
            intent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(intent, REQUEST_PICK_IMAGE)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val bitmap = data?.extras?.get("data") as Bitmap
                beautymodeid.setImageBitmap(bitmap)
            } else if (requestCode == REQUEST_PICK_IMAGE) {
                val uri = data?.getData()
                beautymodeid.setImageURI(uri)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) =
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            makeText(requireContext(), "please accept camera permission", Toast.LENGTH_LONG)
                .show()
        }

    private fun startCamera() {
        processCameraProvider.unbindAll()

        // 2
        val preview = createPreviewUseCase()

        preview.setOnPreviewOutputUpdateListener {
            // 3
            val parent = binding.previewId.parent as ViewGroup
            parent.removeView(binding.previewId)
            parent.addView(binding.previewId, 0)

            binding.previewId.surfaceTexture

        }

        // 5
        imageCapture = createCaptureUseCase()

        // 6
        processCameraProvider.bindToLifecycle(context, preview, imageCapture)

}

    private fun createCaptureUseCase(): CameraSelector {
        // 2
        val cameraSelector = CameraSelector.Builder()
            .apply {
                setLensFacing(lensFacing)

            }

        return CameraSelector(cameraSelector.build())
    }

    private fun createPreviewUseCase(): Preview {
        // 1
        val previewConfig = PreviewConfig.Builder().apply {
            // 2
            setLensFacing(lensFacing)

            // 3
            setTargetRotation(previewView.display.rotation)
        }.build()

        return Preview(previewConfig)
    }
}







package com.ui.main.viewModel

import android.content.Context
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ui.main.view.CameraHomeScreenFragment
import java.io.File

class ViewModelClass : ViewModel() {

    private lateinit var cameraHomeScreenFragment: CameraHomeScreenFragment
    private lateinit var fragmentActivity: FragmentActivity
    val beautyImages: MutableLiveData<String> = MutableLiveData()
    fun init(
        cameraHomeScreenFragment: CameraHomeScreenFragment,
        activity: FragmentActivity
    ) {
        this.fragmentActivity = activity
        this.cameraHomeScreenFragment = cameraHomeScreenFragment;
    }
    fun takePhoto() {
        val photoFile = File(
            fragmentActivity.externalMediaDirs.firstOrNull(),
            "IOSApplication- ${System.currentTimeMillis()}.jpg"
        )
        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//        cameraHomeScreenFragment.imageCapture?.takePicture(
//            output, ContextCompat.getMainExecutor(cameraHomeScreenFragment.context),
//            object : ImageCapture.OnImageCapturedCallback(),
//                ImageCapture.OnImageSavedCallback {
//                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                    (beautyImages as? MutableLiveData)?.value = photoFile.absolutePath
//                    Toast.makeText(
//                        cameraHomeScreenFragment.context,
//                        "save the image",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                override fun onError(exception: androidx.camera.core.ImageCaptureException) {
//                    Toast.makeText(
//                        cameraHomeScreenFragment.context,
//                        "image not store ",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })
    }

}

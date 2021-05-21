package com.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ioscameraandroidapp.R
import com.ui.main.splash.SplashState
import com.ui.main.viewModel.SplashViewModel

class SplashScreenClass : Fragment() {
    private val splashViewModel: SplashViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observerSplashLiveData()
        return inflater.inflate(R.layout.splash_screen, container, false)
    }

    private fun observerSplashLiveData() {

        val observer = Observer<SplashState> { splashState ->
            when (splashState) {

                is SplashState.AuthActivity -> {
                    homeCameraScreenFragment()
                }
            }
        }

        splashViewModel.splashLiveData.observe(requireActivity(), observer)

    }

   private fun homeCameraScreenFragment() {
        val action = SplashScreenClassDirections.actionSplashScreenClassToCameraHomeScreenClass()
        findNavController().navigate(action)
    }
}
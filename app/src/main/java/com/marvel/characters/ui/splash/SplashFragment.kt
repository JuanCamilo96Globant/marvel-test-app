package com.marvel.characters.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.marvel.characters.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        binding.ivSplashIcon.animate().apply {
            duration = 1000
            scaleX(0.0F)
        }.withEndAction {
            val action = SplashFragmentDirections.actionSplashFragmentToCharactersFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}
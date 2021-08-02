package com.example.task8.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task8.R
import com.example.task8.databinding.FragmentHelloBinding
import com.example.task8.view.activity.MainActivity

class HelloFragment : Fragment() {
    private lateinit var binding: FragmentHelloBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelloBinding.inflate(layoutInflater)
        binding.toWebViewBtn.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.action_helloFragment_to_webView)
        }
        binding.toAnimatedHtmlBtn.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.action_helloFragment_to_fragmentAnimatedHtml)
        }
        binding.toGeolocationBtn.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.action_helloFragment_to_geolocationFragment)
        }
        return binding.root
    }
}
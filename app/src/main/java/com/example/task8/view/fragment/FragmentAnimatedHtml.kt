package com.example.task8.view.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import com.example.task8.databinding.FragmentAnimatedHtmlBinding

class FragmentAnimatedHtml : Fragment() {
    private lateinit var binding: FragmentAnimatedHtmlBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimatedHtmlBinding.inflate(layoutInflater)
        ObjectAnimator.ofFloat(binding.myTextView, View.ALPHA, 0f, 1f).apply {
            interpolator = LinearInterpolator()
            duration = 1000
            start()
        }
        return binding.root
    }
}
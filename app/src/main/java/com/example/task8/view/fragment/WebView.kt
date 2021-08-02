package com.example.task8.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task8.databinding.FragmentWebViewBinding
import com.example.task8.network.WebClient

class WebView : Fragment() {

    private lateinit var binding: FragmentWebViewBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        return binding.root
    }

    private fun init() {
        binding = FragmentWebViewBinding.inflate(layoutInflater)

        val webView = binding.webView
        //Можно ли осуществлять работу с WebView во ViewModel?
        webView.settings.apply {
            javaScriptEnabled = true
            loadsImagesAutomatically = true
        }
        webView.webViewClient = WebClient()
        webView.loadUrl("https://www.google.ru/")
    }
}
package com.example.task8.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.task8.R
import com.example.task8.databinding.ActivityMainBinding
/*
1. Реалзовать отображение html контента в webview
2. Создать кастомный элемент на основе TextView. Добавить функцию обработки ввода html текста. Добавить анимацию данного элемента.
3. Опеделить местоположение устройства, отобразить координаты на экране.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val navController by lazy {
        Navigation.findNavController(this, binding.navHostFragment.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
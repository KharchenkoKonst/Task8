package com.example.task8.view

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.task8.R

class MyTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    var htmlText: String? = null
        set(value) {
            field = value
            text = value?.let { Html.fromHtml(value) }
        }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0)
            .also { typedArray ->
                htmlText = typedArray.getString(R.styleable.MyTextView_htmlText)
            }.recycle()
    }
}
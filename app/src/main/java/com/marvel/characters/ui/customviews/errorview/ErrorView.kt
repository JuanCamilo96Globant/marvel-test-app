package com.marvel.characters.ui.customviews.errorview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.marvel.characters.databinding.ErrorResponceLayoutBinding

class ErrorView (context: Context, attrs: AttributeSet?) :
    LinearLayoutCompat(context, attrs) {

    private val binding = ErrorResponceLayoutBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setData(
        errorMessage :String
    ){
        binding.tvError.text = errorMessage
    }

    fun setRetryClickListener(
        onClickListener: OnClickListener
    ){
        binding.ivRetry.setOnClickListener(onClickListener)
        binding.tvRetryLabel.setOnClickListener(onClickListener)
    }
}
package com.marvel.characters.ui.customviews.toogleview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.characters.databinding.ToogleStringListBinding

class ToogleNameListView(context: Context, attrs: AttributeSet?) :
    LinearLayoutCompat(context, attrs) {

    private var isOpen = false
    private val adapter = NameListAdapter()

    private val binding = ToogleStringListBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        binding.rvNames.layoutManager = LinearLayoutManager(context)
        binding.rvNames.adapter = adapter
        addListeners()
    }

    private fun addListeners() {
        binding.toogleContainer.setOnClickListener {
            if (isOpen) {
                binding.rvNames.visibility = View.GONE
                isOpen = false
            } else {
                binding.rvNames.visibility = View.VISIBLE
                isOpen = true
            }
        }
    }

    fun setUp(
        title: String,
        namesList: List<String>
    ) {
        binding.tvTitle.text = "${title} (${namesList.size})"
        adapter.submitList(namesList)
    }

}
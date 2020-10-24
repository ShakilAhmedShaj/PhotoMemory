package com.decimalab.photomemory.ui.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.photomemory.R
import com.decimalab.photomemory.model.Image

/**
 * Displays the tasks from the API, into a list of items.
 */
class ImageAdapter(
    private val onItemLongClick: (String) -> Unit
) : RecyclerView.Adapter<ImageHolder>() {

    private val data: MutableList<Image> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bindData(data[position], onItemLongClick)
    }

    fun setData(data: List<Image>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}
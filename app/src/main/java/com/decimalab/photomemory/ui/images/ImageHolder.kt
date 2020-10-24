package com.decimalab.photomemory.ui.images

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decimalab.photomemory.model.Image
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_image.view.*

/**
 * Holder to display the Task item in a list.
 */
class ImageHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bindData(image: Image, onItemLongClick: (String) -> Unit) {
        containerView.setOnLongClickListener {
            onItemLongClick(image.imagePath)
            true
        }

        Glide.with(containerView).load(image.imagePath).into(containerView.image)
    }
}
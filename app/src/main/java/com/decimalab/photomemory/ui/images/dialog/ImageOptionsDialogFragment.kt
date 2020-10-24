package com.decimalab.photomemory.ui.images.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.decimalab.photomemory.R
import kotlinx.android.synthetic.main.fragment_dialog_image_options.*

/**
 * Displays the options to delete or complete a task.
 */
class ImageOptionsDialogFragment : DialogFragment() {

  private var imageOptionListener: ImageOptionsListener? = null

  companion object {
    private const val KEY_IMAGE_PATH = "image_path"

    fun newInstance(imagePath: String): ImageOptionsDialogFragment = ImageOptionsDialogFragment().apply {
      arguments = Bundle().apply {
        putString(KEY_IMAGE_PATH, imagePath)
      }
    }
  }

  interface ImageOptionsListener {

    fun onImageDownload(imageUrl: String)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_dialog_image_options, container)
  }

  override fun onStart() {
    super.onStart()
    dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  private fun initUi() {
    val imagePath = arguments?.getString(KEY_IMAGE_PATH) ?: ""

    downloadImage.setOnClickListener {
      imageOptionListener?.onImageDownload(imagePath)
      dismissAllowingStateLoss()
    }
  }

  fun setImageOptionsListener(imageOptionsListener: ImageOptionsListener) {
    this.imageOptionListener = imageOptionsListener
  }
}
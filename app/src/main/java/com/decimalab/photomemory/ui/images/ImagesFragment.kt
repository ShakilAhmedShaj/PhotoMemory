package com.decimalab.photomemory.ui.images

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.decimalab.photomemory.App
import com.decimalab.photomemory.R
import com.decimalab.photomemory.model.Image
import com.decimalab.photomemory.networking.NetworkStatusChecker
import com.decimalab.photomemory.ui.images.dialog.ImageOptionsDialogFragment
import com.decimalab.photomemory.utils.gone
import com.decimalab.photomemory.utils.toast
import com.decimalab.photomemory.utils.visible
import kotlinx.android.synthetic.main.fragment_images.*

/**
 * Fetches and displays notes from the API.
 */
class ImagesFragment : Fragment(), ImageOptionsDialogFragment.ImageOptionsListener {

    private val adapter by lazy { ImageAdapter(::onItemSelected) }
    private val remoteApi = App.remoteApi
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initUi()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        imagesRecyclerView.layoutManager = LinearLayoutManager(context)
        imagesRecyclerView.adapter = adapter
        getAllImages()
    }

    private fun initListeners() {
        pullToRefresh.setOnRefreshListener {
            getAllImages()
        }
    }

    private fun onItemSelected(taskId: String) {
        val dialog = ImageOptionsDialogFragment.newInstance(taskId)
        dialog.setImageOptionsListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onImageDownload(imageUrl: String) {
    }

    private fun getAllImages() {
        progress.visible()

        onImageUrlsReceived(listOf(Image("https://www.wallpaperup.com/uploads/wallpapers/2013/03/21/55924/3b61c716155c6fa88f321da6d4655767.jpg")))

//    networkStatusChecker.performIfConnectedToInternet {
//      GlobalScope.launch(Dispatchers.Main) {
//        val result = remoteApi.getImages()
//
//        if (result is Success) {
//          onImageUrlsReceived(result.data)
//        } else {
//          onGetImagesFailed()
//        }
//      }
//    }
    }

    private fun onImageUrlsReceived(data: List<Image>) {
        progress.gone()
        pullToRefresh.isRefreshing = false
        if (data.isNotEmpty()) noData.gone() else noData.visible()

        adapter.setData(data)
    }

    private fun onGetImagesFailed() {
        progress.gone()
        pullToRefresh.isRefreshing = false
        activity?.toast("Failed to fetch images!")
    }
}
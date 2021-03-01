package murillomaciel.com.example.youtubemurillo2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_video_list.*
import murillomaciel.com.example.youtubemurillo2.R
import androidx.navigation.fragment.findNavController
import murillomaciel.com.example.youtubemurillo2.EventObserver
import murillomaciel.com.example.youtubemurillo2.model.entity.Items
import murillomaciel.com.example.youtubemurillo2.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class VideoListFragment : Fragment() {

//    private val mainViewModel: MainViewModel by viewModel(named("mainViewModel"))

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var videoAdapter: VideoAdapter

    private val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

        setUpRecyclerView()

        cl_error.setOnClickListener { mainViewModel.refreshVideos() }

        mainViewModel.refreshVideos()
    }

    private fun setUpObservers() {
        mainViewModel.refreshVideos.observe(viewLifecycleOwner, EventObserver { videos ->
            Log.d("Murillo", "refresh")
            videoAdapter.updateVideos(videos)
        })

        mainViewModel.showError.observe(viewLifecycleOwner, EventObserver { showError ->
            cl_error.visibility = if (showError) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }

    private fun setUpRecyclerView() {
        videoAdapter = VideoAdapter(
                videos = mainViewModel.videos as List<Items>,
                listener = object : VideoListener {
                    override fun onClick(position: Int) {
                        navController.navigate(VideoListFragmentDirections.goToPlayer(mainViewModel.videos[position].id.videoId))
                    }
                }
        )

        rv_videos.adapter = videoAdapter
    }
}
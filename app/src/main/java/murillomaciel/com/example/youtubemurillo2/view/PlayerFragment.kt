package murillomaciel.com.example.youtubemurillo2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.fragment_player.*
import murillomaciel.com.example.youtubemurillo2.R
import murillomaciel.com.example.youtubemurillo2.model.YOUTUBE_API_KEY
import murillomaciel.com.example.youtubemurillo2.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class PlayerFragment : Fragment(), YouTubePlayer.OnInitializedListener {

//    private val mainViewModel: MainViewModel by viewModel(named("mainViewModel"))

    private val mainViewModel: MainViewModel by activityViewModels()

    private val arguments: PlayerFragmentArgs by navArgs()
    private val videoId: String by lazy { arguments.videoId }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ytFragment = childFragmentManager.findFragmentById(R.id.fcv_youtube) as YouTubePlayerSupportFragment?
        ytFragment?.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.onShowVideoPlayer()
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.onCloseVideoPlayer()
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, p2: Boolean) {
        player?.setFullscreen(false)
        player?.setShowFullscreenButton(false)
        player?.loadVideo(videoId)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) = Unit
}
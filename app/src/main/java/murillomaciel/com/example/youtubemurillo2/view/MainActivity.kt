package murillomaciel.com.example.youtubemurillo2.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.toolbar.*
import murillomaciel.com.example.youtubemurillo2.EventObserver
import murillomaciel.com.example.youtubemurillo2.R
import murillomaciel.com.example.youtubemurillo2.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpSearchView()
        setListeners()
    }

    private fun setUpToolbar() {
        tb_search.title = getString(R.string.toolbar_title)
        setSupportActionBar(tb_search)
    }

    private fun setUpSearchView() {

        mts_search.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.onQuerySubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        mts_search.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {

            override fun onSearchViewShown() = Unit

            override fun onSearchViewClosed() = mainViewModel.onQueryClose()
        })
    }

    private fun setListeners() {
        mainViewModel.showToolbar.observe(this, EventObserver { visible ->
            mts_search.visibility = if (visible) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_video_list, menu)
        mts_search.setMenuItem(menu?.findItem(R.id.menu_search_id))
        return super.onCreateOptionsMenu(menu)
    }
}
package com.app.kotlintrends.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.kotlintrends.R
import com.app.kotlintrends.data.api.RetroConnect
import com.app.kotlintrends.data.model.RepositoriesListResponse
import com.app.kotlintrends.utils.Constants.Values.ACCESS_TOKEN
import com.app.kotlintrends.utils.EndlessScrollListener
import com.app.kotlintrends.utils.Status
import com.app.kotlintrends.utils.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var userPage = 1
    private val repositoriesRecyclerView: RecyclerView get() = repositories_recycler_view
    private val swipeRefreshLayout: SwipeRefreshLayout get() = repositories_swipe_to_refresh
    private lateinit var repositoriesAdapter : RepositoriesAdapter
    private val searchRepositoryEditText: EditText get() = repository_name_tv

    private val viewModel: MainViewModel by lazy {
        getViewModel(
            MainViewModel::class.java,
            MainViewModelFactory(RetroConnect.getCampaignsApi())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun getGithubRepositories() {
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        repositoriesRecyclerView.layoutManager = layoutManager
        repositoriesRecyclerView.addOnScrollListener(object :
            EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                userPage++
                viewModel.getGithubRepositoriesData(userPage, ACCESS_TOKEN)
            }
        })
    }

    private fun init() {
        viewModel.getGithubRepositoriesData(1, ACCESS_TOKEN)
        viewModel.callApiEveryHour(1, ACCESS_TOKEN)
        getGithubRepositories()
        setupObserver()
        addSearchDynamic()
        addListenersToView()
    }

    private fun addListenersToView() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getGithubRepositoriesData(1, ACCESS_TOKEN)
            swipeRefreshLayout.isRefreshing = false
        }
    }


    private fun addSearchDynamic() {
        searchRepositoryEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            @SuppressLint("DefaultLocale")
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                repositoriesAdapter.filter!!.filter(searchRepositoryEditText.text.toString())
            }
        })
    }

    private fun setupObserver() {
        viewModel.repositories.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    list_progress_bar.visibility = View.GONE
                    it.data?.let { response ->
                            setResponseToAdapter(response)
                    }
                }
                Status.LOADING -> {
                    list_progress_bar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    list_progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.repositoriesNextPage.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    list_progress_bar.visibility = View.GONE
                    it.data?.let { response ->
                            repositoriesAdapter.addAll(response)
                    }
                }
                Status.LOADING -> {
                    list_progress_bar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    list_progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setResponseToAdapter(response: ArrayList<RepositoriesListResponse>) {
        repositoriesAdapter = RepositoriesAdapter(
            this,
            response
        )
        repositoriesRecyclerView.adapter = repositoriesAdapter
    }


}

package com.nativeteams.stocksscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nativeteams.stocksscreen.databinding.FragmentStocksBinding
import com.nativeteams.stocksscreen.stockItemsRecyclerView.StockListViewAdapter
import com.nativeteams.stocksscreen.view.StockScreenCallbacks
import com.nativeteams.stocksscreen.view.StocksViewModel
import com.nightx.ingale.core.domain.LoadingViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class StocksFragment : Fragment() {

    private lateinit var binding: FragmentStocksBinding
    private val viewModel by viewModels<StocksViewModel>()
    private val callbacks: StockScreenCallbacks
        get() = viewModel

    private val stockAdapter by lazy {
        StockListViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStocksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callbacks.refresh()
        binding.stocksRv.layoutManager = LinearLayoutManager(requireContext())
        binding.stocksRv.adapter = stockAdapter
        binding.swipeRefreshLayout.setOnRefreshListener(callbacks::refresh)

        lifecycleScope.launchWhenResumed {
            viewModel.state.collectLatest { state ->
                if (state.loadingState.isError) {
                    binding.stocksRv.visibility = View.GONE
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = resources.getText(
                        if (state.loadingState == LoadingViewState.NetworkError) {
                            R.string.networkErrorMessage
                        } else R.string.errorMessage
                    )
                } else {
                    binding.stocksRv.visibility = View.VISIBLE
                    binding.errorMessage.visibility = View.GONE
                    stockAdapter.submitList(state.stocks)
                }
                binding.swipeRefreshLayout.isRefreshing = state.loadingState.isLoading
            }
        }
    }
}
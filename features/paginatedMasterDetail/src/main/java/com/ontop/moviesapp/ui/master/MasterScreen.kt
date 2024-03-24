package com.ontop.moviesapp.ui.master

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ontop.moviesapp.R
import com.ontop.moviesapp.ui.SharedViewModel
import com.ontop.moviesapp.ui.shared.components.DebouncedBasicInput
import com.ontop.moviesapp.ui.shared.components.EmptyView
import com.ontop.moviesapp.ui.shared.components.ErrorMessage
import com.ontop.moviesapp.ui.shared.components.LoadingNextPageItem
import com.ontop.moviesapp.ui.shared.components.MovieItem
import com.ontop.moviesapp.ui.shared.components.PageLoader
import com.ontop.network.model.Movie

@Composable
fun MasterScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel<Movie>,
    viewModel: MoviesViewModel = hiltViewModel(),
    onNavigateToDetailsScreen: () -> Unit
) {


    var searchQuery by remember { mutableStateOf("") }
//    val filteredItems = viewModel.searchItems(viewModel.moviesState, searchQuery).collectAsLazyPagingItems()
    val uiState by viewModel.moviesState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }

    Column {
//        Box(modifier = modifier.padding(5.dp)) {
//            DebouncedBasicInput(
//                text = "",
//                onTextChanged = { newText ->
//                    searchQuery = newText
//                }
//            )
//        }

        when (uiState) {
            is UiState.Success -> {
                val data = (uiState as UiState.Success).data
                LazyColumn {
                    items(data) { movie ->
                        MovieItem(
                            modifier,
                            onClick = { movie ->
                                movie?.let {
                                    sharedViewModel.setData(it)
                                    onNavigateToDetailsScreen()
                                }
                            },
                            movie = movie
                        )
                    }
                }

            }

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).message
                Text(text = errorMessage ?: "")
            }

        }

//
//        LazyColumn(modifier = modifier.padding(5.dp)) {
//            items(filteredItems.itemCount) { index ->
//                MovieItem(
//                    modifier,
//                    onClick = { movie ->
//                        movie?.let {
//                            sharedViewModel.setData(it)
//                            onNavigateToDetailsScreen()
//                        }
//                    },
//                    movie = filteredItems[index]
//                )
//            }
//            filteredItems.apply {
//
//                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
//                    }
//
//                    loadState.refresh is LoadState.Error -> {
//                        val error = filteredItems.loadState.refresh as LoadState.Error
//                        item {
//                            ErrorMessage(
//                                modifier = Modifier.fillParentMaxSize(),
//                                message = error.error.localizedMessage ?: "",
//                                onClickRetry = { retry() })
//                        }
//                    }
//
//                    loadState.append is LoadState.Loading -> {
//                        item { LoadingNextPageItem(modifier = Modifier) }
//                    }
//
//                    loadState.append is LoadState.Error -> {
//                        val error = filteredItems.loadState.append as LoadState.Error
//                        item {
//                            ErrorMessage(
//                                modifier = Modifier,
//                                message = error.error.localizedMessage ?: "",
//                                onClickRetry = { retry() })
//                        }
//                    }
//
//                    loadState.source.refresh is LoadState.NotLoading -> {
//                        item { EmptyView(stringResource(R.string.no_data_retrieved)) }
//
//                    }
//                }
//            }
//        }

    }
}






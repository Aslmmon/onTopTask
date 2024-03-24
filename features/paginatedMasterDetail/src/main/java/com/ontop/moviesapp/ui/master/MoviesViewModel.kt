package com.ontop.moviesapp.ui.master

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.ontop.domain.GetMoviesUseCase
import com.ontop.network.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private var _moviesState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading())
    val moviesState: StateFlow<UiState> get() = _moviesState


    init {
        getMovies()
    }

     fun getMovies() {
        var list = mutableListOf<Movie>()

        viewModelScope.launch {
           // try {
                getMoviesUseCase.invoke()
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
                    .collect { pagingData ->

                        pagingData.filter {
                           list.add(it)
                            _moviesState.value = UiState.Success(list)
                            true
                        }
                    }
//            } catch (e: Exception) {
//                Log.e("DDDD",e.toString())
//
//                _moviesState.value = UiState.Error(e.message.toString())
//            }
        }
    }

    fun searchItems(items: Flow<PagingData<Movie>>, query: String): Flow<PagingData<Movie>> {
        return items.map { pagingData ->
            pagingData.filter {
                it.title?.contains(query.removeEmptySpaces(), ignoreCase = true) ?: false
            }
        }
    }
}


sealed class UiState {
    data class Success(val data: MutableList<Movie>) : UiState()
    data class Error(val message: String) : UiState()
    class Loading : UiState()

}

fun String.removeEmptySpaces() = this.filterNot { it.isWhitespace() }


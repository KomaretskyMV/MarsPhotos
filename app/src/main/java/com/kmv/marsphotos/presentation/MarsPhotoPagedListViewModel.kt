package com.kmv.marsphotos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kmv.marsphotos.entity.Photo
import kotlinx.coroutines.flow.Flow

class MarsPhotoPagedListViewModel : ViewModel() {

    val pagedMarsPhoto: Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MarsPhotoPagingSource() }
    ).flow.cachedIn(viewModelScope)
}
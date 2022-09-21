package com.kmv.marsphotos.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kmv.marsphotos.data.MarsPhotoRepository
import com.kmv.marsphotos.domain.GetMarsPhotosUseCase
import com.kmv.marsphotos.entity.Photo

class MarsPhotoPagingSource : PagingSource<Int, Photo>() {
    private val marsPhotoRepository= MarsPhotoRepository()
    private val getMarsPhotosUseCase = GetMarsPhotosUseCase(marsPhotoRepository)

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            getMarsPhotosUseCase.execute("2022-9-1", page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}
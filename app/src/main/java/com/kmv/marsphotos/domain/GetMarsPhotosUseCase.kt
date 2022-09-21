package com.kmv.marsphotos.domain

import com.kmv.marsphotos.data.MarsPhotoRepository
import com.kmv.marsphotos.entity.Photo
import javax.inject.Inject

class GetMarsPhotosUseCase @Inject constructor(
    private val marsPhotoRepository: MarsPhotoRepository
) {
    suspend fun execute(earth_date: String, page: Int): List<Photo> {
        return marsPhotoRepository.getMarsPhotos(earth_date, page)
    }
}
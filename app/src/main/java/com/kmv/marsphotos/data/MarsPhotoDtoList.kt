package com.kmv.marsphotos.data

import com.kmv.marsphotos.entity.MarsPhotosList
import com.kmv.marsphotos.entity.Photo

class MarsPhotoDtoList(
    override val photos: List<Photo>
) : MarsPhotosList
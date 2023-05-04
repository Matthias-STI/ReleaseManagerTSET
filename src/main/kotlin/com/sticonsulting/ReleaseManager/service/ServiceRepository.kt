package com.sticonsulting.ReleaseManager.service

import com.sticonsulting.ReleaseManager.service.entity.Service
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ServiceRepository : CrudRepository<Service, Int> {
    fun getByNameAndVersionNr(name: String, versionNr: Int): Service?

}

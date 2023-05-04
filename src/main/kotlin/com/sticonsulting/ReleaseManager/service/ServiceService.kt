package com.sticonsulting.ReleaseManager.service

import org.springframework.stereotype.Service
import com.sticonsulting.ReleaseManager.service.entity.Service as ServiceEntity

@Service
class ServiceService( private val serviceRepository: ServiceRepository ) {
    fun deploy(name: String, versionNr: Int): Int {
        val service : ServiceEntity? = serviceRepository.getByNameAndVersionNr( name, versionNr)
        if( service != null ) {
            return service.systemVersionNr;
        } else {
            return addDeployment(name, versionNr);
        }
    }

    private fun addDeployment(name: String, versionNr: Int): Int {
        val systemVersion : Int = (getLatestSystemVersion() ?: 0)+1
        val newServiceDeployment : ServiceEntity = ServiceEntity( name, versionNr, systemVersion )
        return serviceRepository.save( newServiceDeployment ).systemVersionNr
    }

    fun getServicesWithSystemVersion(systemVersion: Int): List<ServiceEntity> {
        return getAllWithSystemVersionSmallerEqualThan( systemVersion ).groupBy { it.name }.entries.map { it.value.first() }.sortedBy { it.systemVersionNr };
    }

    fun getLatestSystemVersion(): Int? {
        return getFirstOrderedBySystemVersionDescending()?.systemVersionNr
    }

    private fun getFirstOrderedBySystemVersionDescending() : ServiceEntity? {
        return serviceRepository.findAll().maxByOrNull { it.systemVersionNr }
    }

    private fun getAllWithSystemVersionSmallerEqualThan(systemVersion: Int): List<ServiceEntity> {
        return serviceRepository.findAll().filter { it.systemVersionNr <= systemVersion }.sortedByDescending { it.systemVersionNr }
    }
}

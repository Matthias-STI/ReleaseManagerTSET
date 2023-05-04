package com.sticonsulting.ReleaseManager.service.dto

import com.sticonsulting.ReleaseManager.service.entity.Service

data class ServiceResponse(val name: String, val versionNr: Int) {
    constructor(service: Service) : this(service.name, service.versionNr)
}

data class GetResponse(val services: Array<ServiceResponse>) {
    constructor(services: List<Service>) : this(services.map { ServiceResponse(it) }.toTypedArray())

}

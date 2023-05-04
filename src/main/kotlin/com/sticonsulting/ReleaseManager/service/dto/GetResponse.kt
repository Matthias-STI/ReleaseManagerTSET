package com.sticonsulting.ReleaseManager.service.dto

import com.sticonsulting.ReleaseManager.service.ServiceService
import com.sticonsulting.ReleaseManager.service.entity.Service
import lombok.Getter

data class ServiceResponse( public val name : String, public val versionNr : Int ) {
    constructor( service : Service ) : this( service.name, service.versionNr )
}

data class GetResponse( public val services: Array<ServiceResponse> )
{
    constructor( services : List<Service>) : this( services.map { ServiceResponse(it) }.toTypedArray())

}

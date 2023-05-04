package com.sticonsulting.ReleaseManager.service

import com.sticonsulting.ReleaseManager.service.dto.DeploymentRequest
import com.sticonsulting.ReleaseManager.service.dto.DeploymentResponse
import com.sticonsulting.ReleaseManager.service.dto.GetResponse
import com.sticonsulting.ReleaseManager.service.dto.ServiceResponse
import com.sticonsulting.ReleaseManager.service.entity.Service
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("")
class ServiceApi(private val serviceService : ServiceService ) {

    @PostMapping("deploy")
    fun deploy( @RequestBody deployment : DeploymentRequest) : ResponseEntity<DeploymentResponse>
    {
        return ResponseEntity.ok(DeploymentResponse(serviceService.deploy(deployment.name, deployment.versionNr)));
    }

    @GetMapping("services")
    @ResponseBody
    fun getServices(@RequestParam(name="systemVersion") @NotNull systemVersion : Int ) : Array<ServiceResponse> {
        if( systemVersion <= serviceService.getLatestSystemVersion()?:0 ) return GetResponse(serviceService.getServicesWithSystemVersion(systemVersion)).services;
        return emptyArray()
    }

}
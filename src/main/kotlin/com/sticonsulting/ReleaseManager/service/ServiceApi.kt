package com.sticonsulting.ReleaseManager.service

import com.sticonsulting.ReleaseManager.service.dto.DeploymentRequest
import com.sticonsulting.ReleaseManager.service.dto.DeploymentResponse
import com.sticonsulting.ReleaseManager.service.dto.GetResponse
import com.sticonsulting.ReleaseManager.service.dto.ServiceResponse
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("")
class ServiceApi(private val serviceService: ServiceService) {

    @PostMapping("deploy")
    @ResponseBody
    fun deploy(@RequestBody deployment: DeploymentRequest): DeploymentResponse {
        return DeploymentResponse(serviceService.deploy(deployment.name, deployment.versionNr))
    }

    @GetMapping("services")
    @ResponseBody
    fun getServices(@RequestParam(name = "systemVersion") @NotNull systemVersion: Int): Array<ServiceResponse> {
        if (systemVersion <= (serviceService.getLatestSystemVersion() ?: 0)) return GetResponse(
            serviceService.getServicesWithSystemVersion(
                systemVersion
            )
        ).services
        return emptyArray()
    }

}
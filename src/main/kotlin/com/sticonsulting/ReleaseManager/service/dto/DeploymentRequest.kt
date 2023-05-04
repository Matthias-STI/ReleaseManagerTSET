package com.sticonsulting.ReleaseManager.service.dto

import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class DeploymentRequest (
    val name : String,
    val versionNr : Int
    )

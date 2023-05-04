package com.sticonsulting.ReleaseManager.service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Service (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Int?,

    val name : String
    , val versionNr: Int
    , val systemVersionNr: Int) {

    constructor( name : String, versionNr: Int, systemVersionNr: Int) : this(null,name,versionNr,systemVersionNr)
    constructor() : this( null, "",0,0)
}


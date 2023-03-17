package com.balancendiabetes.domain

import groovy.transform.CompileStatic
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

import javax.validation.constraints.NotNull

@CompileStatic
@Serdeable
@MappedEntity
class User {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    Long id

    @NotNull
    String username

    @NotNull
    String email

    @NotNull
    String password

    String toString() {
        "User{id=$id, username=$username, email=$email, password=$password}"
    }
}
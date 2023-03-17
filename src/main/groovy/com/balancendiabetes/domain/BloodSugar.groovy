package com.balancendiabetes.domain

import groovy.transform.CompileStatic
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

import javax.validation.constraints.NotNull
import java.time.Instant

@CompileStatic
@Serdeable
@MappedEntity
class BloodSugar {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    Long id

    @NotNull
    Long userId

    @NotNull
    int count

    @NotNull
    Instant createdTimestamp = Instant.now()

    String toString() {
        "BloodSugar{id=$id, userId=$userId, count=$count, createdTimestamp=$createdTimestamp}"
    }
}
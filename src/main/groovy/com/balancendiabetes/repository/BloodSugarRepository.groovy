package com.balancendiabetes.repository

import com.balancendiabetes.domain.BloodSugar
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

import java.time.Instant

@JdbcRepository(dialect = Dialect.MYSQL)
abstract class BloodSugarRepository implements PageableRepository<BloodSugar, Long> {

    abstract BloodSugar save(@NonNull Long userId, @NonNull int count, @NonNull Instant createdTimestamp)

    @Query("SELECT * FROM blood_sugar bs WHERE bs.user_id = :userId AND bs.created_timestamp >= :timestamp ORDER BY bs.created_timestamp DESC")
    abstract List<BloodSugar> searchUserBloodSugars(Long userId, Instant timestamp)
}
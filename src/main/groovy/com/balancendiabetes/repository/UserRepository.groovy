package com.balancendiabetes.repository

import com.balancendiabetes.domain.User
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

import javax.validation.constraints.NotBlank

@JdbcRepository(dialect = Dialect.MYSQL)
abstract class UserRepository implements PageableRepository<User, Long> {

    abstract User save(@NonNull @NotBlank String username,
                       @NonNull @NotBlank String email,
                       @NonNull @NotBlank String password)

    @Query("SELECT * FROM user u WHERE u.email = :email AND u.password = :password")
    abstract Optional<User> findUserByCreds(String email, String password)
}
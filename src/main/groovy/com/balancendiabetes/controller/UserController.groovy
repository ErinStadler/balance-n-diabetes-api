package com.balancendiabetes.controller

import com.balancendiabetes.domain.BloodSugar

import com.balancendiabetes.domain.User
import com.balancendiabetes.repository.BloodSugarRepository
import com.balancendiabetes.repository.UserRepository
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

import java.time.Instant
import java.time.temporal.ChronoUnit

@CompileStatic
@ExecuteOn(TaskExecutors.IO)
@Controller('/users')
class UserController {

    protected final UserRepository userRepository
    protected final BloodSugarRepository bsRepository

    UserController(UserRepository userRepository, BloodSugarRepository bloodSugarRepository) {
        this.userRepository = userRepository
        this.bsRepository = bloodSugarRepository
    }

    @Get('/{id}')
    Optional<User> getById(Long id) {
        return userRepository.findById(id)
    }

    @Post('/{id}/sugars')
    HttpResponse<BloodSugar> addBloodSugarCaptureForUserId(Long id, int count) {
        BloodSugar bloodSugar = bsRepository.save(id, count, Instant.now())
        return HttpResponse.created(bloodSugar)
    }

    @Get('/{id}/sugars')
    List<BloodSugar> searchBloodSugarsForUserId(Long id, @Nullable @QueryValue String daysBack) {
        Instant searchDaysBack = Instant.now().minus(daysBack.toInteger() ?: 1, ChronoUnit.DAYS)
        List<BloodSugar> bloodSugars = bsRepository.searchUserBloodSugars(id, searchDaysBack)
        return bloodSugars
    }

    @Post('/login')
    Optional<User> authenticate(String email, String password) {
        return userRepository.findUserByCreds(email, password)
    }

    @Post
    HttpResponse<User> save(@Body User user) {
        User newUser = userRepository.save(user.username, user.email, user.password)

        return HttpResponse.created(newUser)
    }
}
package example.micronaut.controller


import example.micronaut.domain.User
import example.micronaut.repository.UserRepository
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@CompileStatic
@ExecuteOn(TaskExecutors.IO)
@Controller('/users')
class UserController {

    protected final UserRepository userRepository

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Get('/{id}')
    Optional<User> getById(Long id) {
        userRepository
                .findById(id)
    }

    @Post
    HttpResponse<User> save(@Body User user) {
        User newUser = userRepository.save(user.username, user.email, user.password)

        HttpResponse.created(newUser)
    }
}
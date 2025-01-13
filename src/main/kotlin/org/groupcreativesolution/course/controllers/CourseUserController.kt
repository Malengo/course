package org.groupcreativesolution.course.controllers

import com.groupcreativesolution.authuser.enums.UserStatus
import jakarta.validation.Valid
import org.apache.coyote.BadRequestException
import org.groupcreativesolution.course.clients.AuthUserClient
import org.groupcreativesolution.course.dtos.SubscriptionUserDTO
import org.groupcreativesolution.course.dtos.UserDTO
import org.groupcreativesolution.course.service.CourseService
import org.groupcreativesolution.course.service.CourseUserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class CourseUserController(
    private val authUserClient: AuthUserClient,
    private val courseService: CourseService,
    private val courseUserService: CourseUserService
) {

    @GetMapping("/courses/{courseId}/users")
    fun getAllUserByCourse(
        @PathVariable("courseId") courseId: UUID,
        @PageableDefault(page = 0, size = 10, sort = ["userId"], direction = Sort.Direction.DESC) pageable: Pageable
        ): ResponseEntity<Page<UserDTO>> {
        authUserClient.getAllUserby(courseId, pageable).let {
            return ResponseEntity.ok(it)
        }
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/courses/{courseId}/users/subscription")
    fun saveSubscriptionUserInCourse(
        @PathVariable("courseId") courseId: UUID,
        @RequestBody @Valid subscriptionUserDTO: SubscriptionUserDTO
    ): ResponseEntity<Any> {

        val courseModel = courseService.findById(courseId) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found")

        if (courseUserService.existsByCourseAndUserId(courseModel, subscriptionUserDTO.userId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already subscribed to this course")
        }

        try {
            val user = authUserClient.getUserById(subscriptionUserDTO.userId) ?: return ResponseEntity("User not found", HttpStatus.NOT_FOUND)
            if (user.userStatus == UserStatus.BLOCKED) return ResponseEntity("User is blocked", HttpStatus.CONFLICT)
        } catch (exception: BadRequestException) {
            return ResponseEntity("User not found", HttpStatus.NOT_FOUND)
        } catch (exception: Exception) {
            return ResponseEntity("Server ERROR", HttpStatus.INTERNAL_SERVER_ERROR)
        }

        val courseUserModel = courseModel.convertToCourseUserModel(subscriptionUserDTO.userId)
        courseUserService.save(courseUserModel)
        return ResponseEntity.status(HttpStatus.CREATED).body("User subscribed to course")
    }

}
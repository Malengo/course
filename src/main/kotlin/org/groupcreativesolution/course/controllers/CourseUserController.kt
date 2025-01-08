package org.groupcreativesolution.course.controllers

import org.groupcreativesolution.course.clients.CourseClient
import org.groupcreativesolution.course.dtos.UserDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class CourseUserController(private val courseClient: CourseClient) {

    @GetMapping("/courses/{courseId}/users")
    fun getAllUserByCourse(
        @PathVariable("courseId") courseId: java.util.UUID,
        @PageableDefault(page = 0, size = 10, sort = ["userId"], direction = Sort.Direction.DESC) pageable: Pageable
        ): ResponseEntity<Page<UserDTO>> {
        courseClient.getAllUserby(courseId, pageable).let {
            return ResponseEntity.ok(it)
        }
        return ResponseEntity.noContent().build()
    }

}
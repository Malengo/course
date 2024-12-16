package org.groupcreativesolution.course.controllers

import jakarta.validation.Valid
import org.groupcreativesolution.course.dtos.CourseDTO
import org.groupcreativesolution.course.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class CourseController(@Autowired private val courseService: CourseService) {

    @PostMapping
    fun saveCourse(@RequestBody @Valid courseDTO: CourseDTO): ResponseEntity<Any> {
        val courseModel = CourseDTO.fromDTO(courseDTO)
        courseService.saveCourse(courseModel)
        return ResponseEntity<Any>(courseModel, HttpStatus.CREATED)
    }

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable(value = "courseId") courseId: UUID): ResponseEntity<Any> {
        val course = courseService.findById(courseId)
        course?.let {
            courseService.deleteCourse(it)
            return ResponseEntity<Any>("Course delete success", HttpStatus.OK)
        }
        return ResponseEntity<Any>("Course not found", HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{courseId}")
    fun updateCourse(
        @PathVariable(value = "courseId") courseId: UUID,
        @RequestBody @Valid courseDTO: CourseDTO
    ): ResponseEntity<Any> {
        val course = courseService.findById(courseId)
        course?.let {
            val courseModel = CourseDTO.fromDTO(courseDTO)
            courseModel.courseId = course.courseId
            courseModel.createdAt = course.createdAt
            courseService.saveCourse(courseModel)
            return ResponseEntity<Any>(courseModel, HttpStatus.OK)
        }
        return ResponseEntity<Any>("Course not found", HttpStatus.NOT_FOUND)
    }

    @GetMapping()
    fun getAllCourses(): ResponseEntity<Any> {
        val courses = courseService.findAllCourses()
        return ResponseEntity<Any>(courses, HttpStatus.OK)
    }

    @GetMapping("/{courseId}")
    fun getCourseById(@PathVariable(value = "courseId") courseId: UUID): ResponseEntity<Any> {
        val course = courseService.findById(courseId)
        course?.let {
            return ResponseEntity<Any>(course, HttpStatus.OK)
        }
        return ResponseEntity<Any>("Course not found", HttpStatus.NOT_FOUND)
    }
}
package org.groupcreativesolution.course.controllers

import jakarta.validation.Valid
import org.groupcreativesolution.course.dtos.CourseDTO
import org.groupcreativesolution.course.repositories.specifications.CourseModelSpecification
import org.groupcreativesolution.course.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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
        val courseModel = courseService.saveCourse(courseDTO)
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
            val courseModel = courseService.updateCourse(courseDTO, course)
            return ResponseEntity<Any>(courseModel, HttpStatus.OK)
        }
        return ResponseEntity<Any>("Course not found", HttpStatus.NOT_FOUND)
    }

    @GetMapping()
    fun getAllCourses(
        @PageableDefault(page = 0, size = 10, sort = ["courseId"], direction = Sort.Direction.ASC) pageable: Pageable,
        @RequestParam(value = "userId", required = false) userId: UUID?,
        specification: CourseModelSpecification
    ): ResponseEntity<Any> {
        val courses = if (userId != null) {
            courseService.findAllCoursesPageable(pageable, specification.getCourseByUserId(userId).and(specification))
        } else {
            courseService.findAllCoursesPageable(pageable, specification)
        }
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
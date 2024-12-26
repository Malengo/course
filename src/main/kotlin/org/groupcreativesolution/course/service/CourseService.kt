package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.dtos.CourseDTO
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.repositories.sprcifications.CourseModelSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CourseService {
    fun deleteCourse(course: CourseModel)
    fun saveCourse(courseDTO: CourseDTO): CourseModel
    fun findById(courseId: UUID): CourseModel?
    fun findAllCourses(): Collection<CourseModel>
    fun updateCourse(courseDTO: CourseDTO, course: CourseModel): CourseModel
    fun findAllCoursesPageable(pageable: Pageable, specification: CourseModelSpecification): Page<CourseModel>
}
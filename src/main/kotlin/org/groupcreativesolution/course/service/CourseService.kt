package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.dtos.CourseDTO
import org.groupcreativesolution.course.models.CourseModel
import java.util.*

interface CourseService {
    fun deleteCourse(course: CourseModel)
    fun saveCourse(courseDTO: CourseDTO): CourseModel
    fun findById(courseId: UUID): CourseModel?
    fun findAllCourses(): Collection<CourseModel>
    fun updateCourse(course: CourseModel)
}
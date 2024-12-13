package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.models.CourseModel
import java.util.*

interface  CourseService {
    fun deleteCourse(course: CourseModel)
    fun saveCourse(course: CourseModel)
    fun findById(courseId: UUID): CourseModel?
}
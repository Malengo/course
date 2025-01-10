package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.models.CourseUserModel
import java.util.*

interface CourseUserService {
    fun existsByCourseAndUserId(courseModel: CourseModel , userId: UUID): Boolean
    fun save(courseUserModel: CourseUserModel)
}
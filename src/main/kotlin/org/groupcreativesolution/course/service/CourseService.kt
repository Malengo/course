package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.models.CourseModel

interface  CourseService {
    fun deleteCourse(course: CourseModel)
}
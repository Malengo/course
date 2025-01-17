package org.groupcreativesolution.course.dtos

import java.util.*

data class CourseUserDTO(
    val courseId: UUID,
    val userId: UUID?
)

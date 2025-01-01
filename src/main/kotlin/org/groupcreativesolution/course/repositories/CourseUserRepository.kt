package org.groupcreativesolution.course.repositories

import org.groupcreativesolution.course.models.CourseUserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CourseUserRepository: JpaRepository<CourseUserModel, UUID> {
}
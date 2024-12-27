package org.groupcreativesolution.course.repositories

import org.groupcreativesolution.course.models.CourseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.UUID

interface CourseRepository: JpaRepository<CourseModel, UUID>, JpaSpecificationExecutor<CourseModel> {
}
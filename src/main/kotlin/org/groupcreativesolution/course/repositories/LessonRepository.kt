package org.groupcreativesolution.course.repositories

import org.groupcreativesolution.course.models.LessonModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LessonRepository: JpaRepository<LessonModel, UUID> {
}
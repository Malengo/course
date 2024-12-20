package org.groupcreativesolution.course.repositories

import org.groupcreativesolution.course.models.LessonModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface LessonRepository : JpaRepository<LessonModel, UUID> {

    @Query("SELECT * FROM lesson l where l.module_module_id = :moduleId", nativeQuery = true)
    fun findAllLessonByModuleId(moduleId: UUID): Collection<LessonModel>

    @Query("SELECT * FROM lesson l where l.module_module_id = :moduleId and l.lesson_id = :lessonId", nativeQuery = true)
    fun findLessonIntoModule(moduleId: UUID, lessonId: UUID): LessonModel?
}
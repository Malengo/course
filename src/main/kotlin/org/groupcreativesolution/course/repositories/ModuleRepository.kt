package org.groupcreativesolution.course.repositories

import org.groupcreativesolution.course.models.ModuleModels
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ModuleRepository: JpaRepository<ModuleModels, UUID> {

    @Query("SELECT * FROM modules m where m.course_course_id = :courseId", nativeQuery = true)
    fun findAllModuleByCourseId(courseId: UUID): Collection<ModuleModels>
}
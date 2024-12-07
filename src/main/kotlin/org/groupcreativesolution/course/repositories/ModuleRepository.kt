package org.groupcreativesolution.course.repositories

import org.groupcreativesolution.course.models.ModuleModels
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ModuleRepository: JpaRepository<ModuleModels, UUID> {
}
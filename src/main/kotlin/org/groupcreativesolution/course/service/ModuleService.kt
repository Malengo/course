package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.dtos.ModuleDTO
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.models.ModuleModels
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.UUID

interface ModuleService {
    fun findAllModuleByCourseId(courdeId: UUID): Collection<ModuleModels>
    fun deleteAllModule(moduleList: Collection<ModuleModels>)
    fun findModuleById(moduleId: UUID): ModuleModels?
    fun saveModule(course: CourseModel, moduleDTO: ModuleDTO): ModuleModels
    fun findModuleIntoCourse(courseId: UUID, moduleId: UUID): ModuleModels?
    fun deleteModule(module: ModuleModels)
    fun updateModule(moduleDTO: ModuleDTO, module: ModuleModels): ModuleModels
    fun findAllModuleByCourseIdAnPageable(pageable: Pageable, specification: Specification<ModuleModels>): Page<ModuleModels>

}
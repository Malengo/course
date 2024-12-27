package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.dtos.ModuleDTO
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.models.ModuleModels
import org.groupcreativesolution.course.repositories.ModuleRepository
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

@Service
class ModuleServiceImpl(@Autowired private val repository: ModuleRepository) : ModuleService {
    override fun findAllModuleByCourseId(courdeId: UUID): Collection<ModuleModels> {
        return repository.findAllModuleByCourseId(courdeId)
    }

    override fun deleteAllModule(moduleList: Collection<ModuleModels>) {
        repository.deleteAll(moduleList)
    }

    override fun findModuleById(moduleId: UUID): ModuleModels? {
        return repository.findById(moduleId).orElse(null)
    }

    override fun saveModule(course: CourseModel, moduleDTO: ModuleDTO): ModuleModels {
        val module = ModuleDTO.fromDTO(moduleDTO)
        module.course = course
        repository.save(module)
        return module
    }

    override fun findModuleIntoCourse(courseId: UUID, moduleId: UUID): ModuleModels? {
        return repository.findModuleIntoCourse(courseId, moduleId)
    }

    override fun deleteModule(module: ModuleModels) {
        repository.delete(module)
    }

    override fun updateModule(moduleDTO: ModuleDTO, module: ModuleModels): ModuleModels {
        val moduleModels = ModuleDTO.fromDTO(moduleDTO)
        moduleModels.moduleId = module.moduleId
        module.creationDate = module.creationDate
        repository.save(module)
        return module
    }

    override fun findAllModuleByCourseIdAnPageable(
        pageable: Pageable,
        specification: Specification<ModuleModels>
    ): Page<ModuleModels> {
        return repository.findAll(specification, pageable)
    }
}
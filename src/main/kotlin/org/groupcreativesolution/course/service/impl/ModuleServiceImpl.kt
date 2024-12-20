package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.models.ModuleModels
import org.groupcreativesolution.course.repositories.ModuleRepository
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired
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
}
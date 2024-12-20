package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.models.ModuleModels
import java.util.UUID

interface ModuleService {
    fun findAllModuleByCourseId(courdeId: UUID): Collection<ModuleModels>
    fun deleteAllModule(moduleList: Collection<ModuleModels>)
    fun findModuleById(moduleId: UUID): ModuleModels?

}
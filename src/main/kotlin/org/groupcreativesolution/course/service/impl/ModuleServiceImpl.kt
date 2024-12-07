package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.repositories.ModuleRepository
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ModuleServiceImpl(@Autowired private val repository: ModuleRepository ): ModuleService {
}
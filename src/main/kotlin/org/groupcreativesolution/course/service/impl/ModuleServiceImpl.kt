package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.repositories.ModuleRepository
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired

class ModuleServiceImpl(@Autowired var repository: ModuleRepository ): ModuleService {
}
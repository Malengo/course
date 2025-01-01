package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.repositories.CourseUserRepository
import org.groupcreativesolution.course.service.CourseUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseUserServiceImpl(@Autowired private val courseUserRepository: CourseUserRepository) :
    CourseUserService {
}
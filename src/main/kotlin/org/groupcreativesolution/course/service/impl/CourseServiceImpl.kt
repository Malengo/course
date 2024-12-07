package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.repositories.CourseRepository
import org.groupcreativesolution.course.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(@Autowired private val repository: CourseRepository): CourseService {
}
package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.repositories.LessonRepository
import org.groupcreativesolution.course.service.LessonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LessonServiceImpl(@Autowired private val lessonRepository: LessonRepository): LessonService {
}
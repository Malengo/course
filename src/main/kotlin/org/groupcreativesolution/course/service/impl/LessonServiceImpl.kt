package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.models.LessonModel
import org.groupcreativesolution.course.repositories.LessonRepository
import org.groupcreativesolution.course.service.LessonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class LessonServiceImpl(@Autowired private val lessonRepository: LessonRepository) : LessonService {
    override fun findAllLessonByModuleId(moduleId: UUID): Collection<LessonModel> {
        return lessonRepository.findAllLessonByModuleId(moduleId)
    }

    override fun deleteAllLesson(lessonList: Collection<LessonModel>) {
        lessonRepository.deleteAll(lessonList)
    }
}
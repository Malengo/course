package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.models.LessonModel
import java.util.*

interface LessonService {
    fun findAllLessonByModuleId(moduleId: UUID): Collection<LessonModel>
    fun deleteAllLesson(lessonList: Collection<LessonModel>)
}
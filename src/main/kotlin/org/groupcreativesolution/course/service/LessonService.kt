package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.dtos.LessonDTO
import org.groupcreativesolution.course.models.LessonModel
import org.groupcreativesolution.course.models.ModuleModels
import java.util.*

interface LessonService {
    fun findAllLessonByModuleId(moduleId: UUID): Collection<LessonModel>
    fun deleteAllLesson(lessonList: Collection<LessonModel>)
    fun saveLesson(module: ModuleModels, lessonDTO: LessonDTO): LessonModel
    fun findLessonIntoModule(moduleId: UUID, lessonId: UUID): LessonModel?
    fun deleteLesson(lesson: LessonModel)
    fun updateLesson(lessonDTO: LessonDTO, lesson: LessonModel): LessonModel
}
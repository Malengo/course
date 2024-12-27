package org.groupcreativesolution.course.service

import org.groupcreativesolution.course.dtos.LessonDTO
import org.groupcreativesolution.course.models.LessonModel
import org.groupcreativesolution.course.models.ModuleModels
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.*

interface LessonService {
    fun findAllLessonByModuleId(moduleId: UUID): Collection<LessonModel>
    fun deleteAllLesson(lessonList: Collection<LessonModel>)
    fun saveLesson(module: ModuleModels, lessonDTO: LessonDTO): LessonModel
    fun findLessonIntoModule(moduleId: UUID, lessonId: UUID): LessonModel?
    fun deleteLesson(lesson: LessonModel)
    fun updateLesson(lessonDTO: LessonDTO, lesson: LessonModel): LessonModel
    fun findAllLessonByModuleIdAndPageable(specification: Specification<LessonModel>, pageable: Pageable): Page<LessonModel>
}
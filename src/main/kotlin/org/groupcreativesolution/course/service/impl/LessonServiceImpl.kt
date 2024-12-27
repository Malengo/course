package org.groupcreativesolution.course.service.impl

import org.groupcreativesolution.course.dtos.LessonDTO
import org.groupcreativesolution.course.models.LessonModel
import org.groupcreativesolution.course.models.ModuleModels
import org.groupcreativesolution.course.repositories.LessonRepository
import org.groupcreativesolution.course.service.LessonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
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

    override fun saveLesson(module: ModuleModels, lessonDTO: LessonDTO): LessonModel {
        val lessonModel = LessonDTO.fromDTO(lessonDTO)
        lessonModel.module = module
        lessonRepository.save(lessonModel)
        return lessonModel
    }

    override fun findLessonIntoModule(moduleId: UUID, lessonId: UUID): LessonModel? {
        return lessonRepository.findLessonIntoModule(moduleId, lessonId)
    }

    override fun deleteLesson(lesson: LessonModel) {
        lessonRepository.delete(lesson)
    }

    override fun updateLesson(lessonDTO: LessonDTO, lesson: LessonModel): LessonModel {
        val lessonModel = LessonDTO.fromDTO(lessonDTO)
        lessonModel.lessonId = lesson.lessonId
        lessonModel.creationDate = lesson.creationDate
        lessonRepository.save(lessonModel)
        return lessonModel
    }

    override fun findAllLessonByModuleIdAndPageable(
        specification: Specification<LessonModel>,
        pageable: Pageable
    ): Page<LessonModel> {
        return lessonRepository.findAll(specification, pageable)
    }
}
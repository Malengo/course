package org.groupcreativesolution.course.service.impl

import jakarta.transaction.Transactional
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.repositories.CourseRepository
import org.groupcreativesolution.course.service.CourseService
import org.groupcreativesolution.course.service.LessonService
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseServiceImpl(
    @Autowired private val courseRepository: CourseRepository,
    @Autowired private val moduleService: ModuleService,
    @Autowired private val lessonService: LessonService
) : CourseService {

    @Transactional
    override fun deleteCourse(course: CourseModel) {
        val moduleList = course.courseId?.let { moduleService.findAllModuleByCourseId(it) }

        moduleList?.forEach { module ->
            val lessonList = module.moduleId?.let { lessonService.findAllLessonByModuleId(it) }
            lessonList?.let { lessonService.deleteAllLesson(it) }
        }

        moduleList?.let { moduleService.deleteAllModule(it) }
        courseRepository.delete(course)
    }

    override fun saveCourse(course: CourseModel) {
        courseRepository.save(course)
    }

    override fun findById(courseId: UUID): CourseModel? {
        return courseRepository.findById(courseId).orElse(null)
    }

    override fun findAllCourses(): Collection<CourseModel> {
        return courseRepository.findAll()
    }
}
package org.groupcreativesolution.course.service.impl

import jakarta.transaction.Transactional
import org.groupcreativesolution.course.dtos.CourseDTO
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.repositories.CourseRepository
import org.groupcreativesolution.course.repositories.sprcifications.CourseModelSpecification
import org.groupcreativesolution.course.service.CourseService
import org.groupcreativesolution.course.service.LessonService
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    override fun saveCourse(courseDTO: CourseDTO): CourseModel {
        val course = CourseDTO.fromDTO(courseDTO)
        courseRepository.save(course)
        return course
    }

    override fun findById(courseId: UUID): CourseModel? {
        return courseRepository.findById(courseId).orElse(null)
    }

    override fun findAllCourses(): Collection<CourseModel> {
        return courseRepository.findAll()
    }

    override fun updateCourse(courseDTO: CourseDTO, course: CourseModel): CourseModel {
        val courseModel = CourseDTO.fromDTO(courseDTO)
        courseModel.courseId = course.courseId
        courseModel.createdAt = course.createdAt
        courseRepository.save(courseModel)
        return courseModel
    }

    override fun findAllCoursesPageable(
        pageable: Pageable,
        specification: CourseModelSpecification
    ): Page<CourseModel> {
        return courseRepository.findAll(specification, pageable)
    }
}
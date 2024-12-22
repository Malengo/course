package org.groupcreativesolution.course.controllers

import jakarta.validation.Valid
import org.groupcreativesolution.course.dtos.ModuleDTO
import org.groupcreativesolution.course.service.CourseService
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
class ModuleController(
    @Autowired private val moduleService: ModuleService,
    @Autowired private val courseService: CourseService
) {

    @PostMapping("/api/v1/modules/{courseId}/module")
    fun saveModule(
        @PathVariable(value = "courseId") courseId: UUID,
        @RequestBody @Valid moduleDTO: ModuleDTO
    ): ResponseEntity<Any> {
        val course = courseService.findById(courseId)
        course?.let {
            val module = moduleService.saveModule(course, moduleDTO)
            return ResponseEntity(module, HttpStatus.CREATED)
        }
        return ResponseEntity("Course not found", HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/api/v1/modules/{courseId}/module/{moduleId}")
    fun deleteModule(
        @PathVariable(value = "courseId") courseId: UUID,
        @PathVariable(value = "moduleId") moduleId: UUID
    ): ResponseEntity<Any> {
        val module = moduleService.findModuleIntoCourse(courseId, moduleId)
        module?.let {
            moduleService.deleteModule(it)
            return ResponseEntity("Module delete success", HttpStatus.OK)
        }
        return ResponseEntity("Module not found for this course", HttpStatus.NOT_FOUND)
    }

    @PutMapping("/api/v1/modules/{courseId}/module/{moduleId}")
    fun updateModule(
        @PathVariable(value = "courseId") courseId: UUID,
        @PathVariable(value = "moduleId") moduleId: UUID,
        @RequestBody @Valid moduleDTO: ModuleDTO
    ): ResponseEntity<Any> {
        val module = moduleService.findModuleIntoCourse(courseId, moduleId)
        module?.let {
            val updatedModule = moduleService.updateModule(moduleDTO, it)
            return ResponseEntity(updatedModule, HttpStatus.OK)
        }
        return ResponseEntity("Module not found for this course", HttpStatus.NOT_FOUND)
    }

    @GetMapping("/api/v1/modules/{courseId}/module")
    fun findAllModuleByCourseId(@PathVariable(value = "courseId") courseId: UUID): ResponseEntity<Any> {
        val modules = moduleService.findAllModuleByCourseId(courseId)
        return ResponseEntity(modules, HttpStatus.OK)
    }

    @GetMapping("/api/v1/modules/{courseId}/module/{moduleId}")
    fun getModuleByCourseId(
        @PathVariable(value = "courseId") courseId: UUID,
        @PathVariable(value = "moduleId") moduleId: UUID
    ): ResponseEntity<Any> {
        val module = moduleService.findModuleIntoCourse(courseId, moduleId)
        module?.let {
            return ResponseEntity(module, HttpStatus.OK)
        }
        return ResponseEntity("Module not found for this course", HttpStatus.NOT_FOUND)
    }

}
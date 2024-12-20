package org.groupcreativesolution.course.controllers

import jakarta.validation.Valid
import org.groupcreativesolution.course.dtos.LessonDTO
import org.groupcreativesolution.course.service.LessonService
import org.groupcreativesolution.course.service.ModuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
class LessonController(
    @Autowired private val lessonService: LessonService,
    @Autowired private val moduleService: ModuleService
) {

    @PostMapping("/api/v1/modules/{moduleId}/lessons")
    fun saveLesson(
        @PathVariable(value = "moduleId") moduleId: UUID,
        @RequestBody @Valid lessonDTO: LessonDTO
    ): ResponseEntity<Any> {
        val module = moduleService.findModuleById(moduleId)
        module?.let {
            val lessonModel = lessonService.saveLesson(module, lessonDTO)
            return ResponseEntity<Any>(lessonModel, HttpStatus.CREATED)
        }
        return ResponseEntity<Any>("Module not found", HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/api/v1/modules/{moduleId}/lessons/{lessonId}")
    fun deleteLesson(
        @PathVariable(value = "moduleId") moduleID: UUID,
        @PathVariable(value = "lessonId") lessonId: UUID
    ): ResponseEntity<Any> {
        val lesson = lessonService.findLessonIntoModule(moduleID, lessonId)
        lesson?.let {
            lessonService.deleteLesson(it)
            return ResponseEntity<Any>("Lesson delete success", HttpStatus.OK)
        }
        return ResponseEntity<Any>("Lesson not found for this module", HttpStatus.NOT_FOUND)
    }

    @PutMapping("/api/v1/modules/{moduleId}/lessons/{lessonId}")
    fun updateLesson(
        @PathVariable(value = "moduleId") moduleId: UUID,
        @PathVariable(value = "lessonId") lessonId: UUID,
        @RequestBody @Valid lessonDTO: LessonDTO
    ): ResponseEntity<Any> {
        val lesson = lessonService.findLessonIntoModule(moduleId, lessonId)
        lesson?.let {
            val lessonModel = lessonService.updateLesson(lessonDTO, it)
            return ResponseEntity<Any>(lessonModel, HttpStatus.OK)
        }
        return ResponseEntity<Any>("Lesson not found for this module", HttpStatus.NOT_FOUND)
    }

    @GetMapping("/api/v1/modules/{moduleId}/lessons")
    fun getAllLessonsByModuleId(@PathVariable(value = "moduleId") moduleId: UUID): ResponseEntity<Any> {
        val lessons = lessonService.findAllLessonByModuleId(moduleId)
        return ResponseEntity<Any>(lessons, HttpStatus.OK)
    }

    @GetMapping("/api/v1/modules/{moduleId}/lessons/{lessonId}")
    fun getLessonByModuleId(
        @PathVariable(value = "moduleId") moduleId: UUID,
        @PathVariable(value = "lessonId") lessonId: UUID
    ): ResponseEntity<Any> {
        val lesson = lessonService.findLessonIntoModule(moduleId, lessonId)
        lesson?.let {
            return ResponseEntity<Any>(lesson, HttpStatus.OK)
        }
        return ResponseEntity<Any>("Lesson not found for this module", HttpStatus.NOT_FOUND)
    }
}
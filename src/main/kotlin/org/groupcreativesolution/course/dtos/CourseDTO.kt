package org.groupcreativesolution.course.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.groupcreativesolution.course.enuns.CourseLevel
import org.groupcreativesolution.course.enuns.CourseStatus
import org.groupcreativesolution.course.models.CourseModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class CourseDTO(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,

    val imgUrl: String?,

    @field:NotNull
    val courseStatus: CourseStatus?,

    @field:NotNull
    val userInstructor: UUID?,

    @field:NotNull
    val courseLevel: CourseLevel?
    ) {

    companion object {
        private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

        fun fromDTO(courseDTO: CourseDTO): CourseModel {
            val now = LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS).format(formatter)
            return CourseModel (
                courseName = courseDTO.name,
                description = courseDTO.description,
                imageUrl = courseDTO.imgUrl,
                courseStatus = courseDTO.courseStatus,
                userInstructor = courseDTO.userInstructor,
                courseLevel = courseDTO.courseLevel,
                createdAt = now,
                updatedAt = now
            )
        }
    }
}
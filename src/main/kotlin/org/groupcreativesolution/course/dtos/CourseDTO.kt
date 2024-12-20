package org.groupcreativesolution.course.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.groupcreativesolution.course.enuns.CourseLevel
import org.groupcreativesolution.course.enuns.CourseStatus
import org.groupcreativesolution.course.models.CourseModel
import java.util.*

data class CourseDTO(

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

        fun fromDTO(courseDTO: CourseDTO): CourseModel {

            return CourseModel(
                courseName = courseDTO.name,
                description = courseDTO.description,
                imageUrl = courseDTO.imgUrl,
                courseStatus = courseDTO.courseStatus,
                userInstructor = courseDTO.userInstructor,
                courseLevel = courseDTO.courseLevel,
                createdAt = CommomDTO.now,
                updatedAt = CommomDTO.now
            )
        }
    }
}
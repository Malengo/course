package org.groupcreativesolution.course.dtos

import jakarta.validation.constraints.NotBlank
import org.groupcreativesolution.course.models.LessonModel

data class LessonDTO(
    @NotBlank
    val title: String?,

    val description: String?,

    @NotBlank
    val videoUrl: String?,
) {
    companion object {
        fun fromDTO(lessonDTO: LessonDTO): LessonModel {
            return LessonModel(
                description = lessonDTO.description,
                title = lessonDTO.title,
                videoUrl = lessonDTO.videoUrl,
                creationDate = CommomDTO.now,
            )
        }
    }
}

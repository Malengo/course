package org.groupcreativesolution.course.dtos

import jakarta.validation.constraints.NotBlank
import org.groupcreativesolution.course.models.ModuleModels

data class ModuleDTO(
    @NotBlank
    val name: String?,

    @NotBlank
    val description: String?
) {
    companion object {
        fun fromDTO(moduleDTO: ModuleDTO): ModuleModels {
            return ModuleModels(
                title = moduleDTO.name,
                description = moduleDTO.description,
                creationDate = CommomDTO.now
            )
        }
    }
}

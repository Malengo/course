package org.groupcreativesolution.course.dtos

import jakarta.validation.constraints.NotNull
import java.util.*

data class SubscriptionUserDTO(
    @field:NotNull
    val userId: UUID,

    )

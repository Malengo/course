package org.groupcreativesolution.course.dtos

import com.groupcreativesolution.authuser.enums.UserStatus
import com.groupcreativesolution.authuser.enums.UserType
import java.util.*

data class UserDTO(
    val userId: UUID,
    val username: String,
    val email: String,
    val fullName: String,
    val userStatus: UserStatus,
    val userType: UserType,
    val phoneNumber: String,
    val cpf: String,
    val image: String
)

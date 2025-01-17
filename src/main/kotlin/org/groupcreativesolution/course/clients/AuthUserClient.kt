package org.groupcreativesolution.course.clients

import jakarta.servlet.http.HttpServletResponse
import org.apache.coyote.BadRequestException
import org.groupcreativesolution.course.dtos.CourseUserDTO
import org.groupcreativesolution.course.dtos.RestResponsePage
import org.groupcreativesolution.course.dtos.UserDTO
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.util.*


@Component
class AuthUserClient(
    private val restClient: RestClient
) {

    fun getAllUserby(courseId: UUID, pageable: Pageable): Page<UserDTO> {
        val searchResult: Page<UserDTO>?
        val url = "/users?courseId=$courseId&page=${pageable.pageNumber}&size=${pageable.pageSize}&sort=${pageable.sort.toString().replace(": ", ",")}"

        try {
            val responseType = object : ParameterizedTypeReference<RestResponsePage<UserDTO>>() {}
            searchResult = restClient.get()
                .uri(url)
                .retrieve()
                .body(responseType)
            searchResult?.let { return it }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Page.empty()
    }

    fun getUserById(userId: UUID): UserDTO? {
        val url = "/users/$userId"
        val userDTO = restClient.get()
            .uri(url)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _ ,_ ->
                throw BadRequestException("User not found")
            }
            .body(UserDTO::class.java)
        return userDTO
    }

    fun postSubscriptionUserInCourse(courseId: UUID, userId: UUID) {
        val url = "/users/$userId/courses/subscription"
        val courseUserDTO = CourseUserDTO(courseId, userId)
        try {
            restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(courseUserDTO)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError) {_, response ->
                    when (response.statusCode) {
                        HttpStatus.CONFLICT -> throw BadRequestException("User already subscribed to this course")
                        HttpStatus.NOT_FOUND -> throw BadRequestException("User not found")
                        else -> throw RuntimeException("Unexpected error: ${response.statusCode}")
                    }
                }
                .toBodilessEntity()
        } catch (e: Exception) {
            throw RuntimeException("Error communicating with the server: ${e.message}")
        }
    }
}
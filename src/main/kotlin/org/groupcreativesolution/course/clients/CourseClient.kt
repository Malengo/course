package org.groupcreativesolution.course.clients

import org.groupcreativesolution.course.dtos.RestResponsePage
import org.groupcreativesolution.course.dtos.UserDTO
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.util.*

@Component
class CourseClient(private val restClient: RestClient) {

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
}
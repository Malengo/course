package org.groupcreativesolution.course.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.groupcreativesolution.course.dtos.CourseDTO
import org.groupcreativesolution.course.enuns.CourseLevel
import org.groupcreativesolution.course.enuns.CourseStatus
import org.groupcreativesolution.course.service.CourseService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.util.*

@WebMvcTest
class CourseControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var courseService: CourseService

    @Test
    fun `saveCourse should return 201 when course created`() {
        //given
        val courseRequest = CourseDTO(
            name = "Java",
            description = "Java Programming",
            imgUrl = "https://www.google.com",
            courseStatus = CourseStatus.PROGRESS,
            courseLevel = CourseLevel.BASIC,
            userInstructor = UUID.randomUUID()
        )

        //when
        val courseResponse = CourseDTO.fromDTO(courseRequest)

        every { courseService.saveCourse(any()) } returns courseResponse

        //then
        mockMvc.post("/api/v1/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(courseRequest)
        }.andExpect {
            status { isCreated() }
            content { json(ObjectMapper().writeValueAsString(courseResponse)) }
        }

    }

    @Test
    fun deleteCourse() {
    }

    @Test
    fun updateCourse() {
    }

    @Test
    fun getAllCourses() {
    }

    @Test
    fun getCourseById() {
    }
}
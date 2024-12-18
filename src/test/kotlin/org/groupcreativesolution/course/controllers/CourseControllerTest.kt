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
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
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
    fun `saveCourse should return 400 and send message when course name is empty`() {
        //given
        val courseRequest = CourseDTO(
            name = "",
            description = "Java Programming",
            imgUrl = "https://www.google.com",
            courseStatus = CourseStatus.PROGRESS,
            courseLevel = CourseLevel.BASIC,
            userInstructor = UUID.randomUUID()
        )

        //then
        mockMvc.post("/api/v1/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(courseRequest)
        }.andExpect {
            status { isBadRequest() }
            content {
                string(
                    """
                   {"name":"must not be blank"}
            """.trimIndent()
                )
            }
        }
    }

    @Test
    fun `saveCourse should return 400 and send message when course description is empty`() {
        //given
        val courseRequest = CourseDTO(
            name = "Java",
            description = "",
            imgUrl = "https://www.google.com",
            courseStatus = CourseStatus.PROGRESS,
            courseLevel = CourseLevel.BASIC,
            userInstructor = UUID.randomUUID()
        )

        //then
        mockMvc.post("/api/v1/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(courseRequest)
        }.andExpect {
            status { isBadRequest() }
            content {
                string(
                    """
                   {"description":"must not be blank"}
            """.trimIndent()
                )
            }
        }
    }

    @Test
    fun `saveCourse should return 400 and send message when course status is null`() {
        //given
        val courseRequest = CourseDTO(
            name = "Java",
            description = "Test",
            imgUrl = "https://www.google.com",
            courseStatus = null,
            courseLevel = CourseLevel.BASIC,
            userInstructor = UUID.randomUUID()
        )

        //then
        mockMvc.post("/api/v1/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(courseRequest)
        }.andExpect {
            status { isBadRequest() }
            content {
                string(
                    """
                   {"courseStatus":"must not be null"}
            """.trimIndent()
                )
            }
        }
    }

    @Test
    fun `saveCourse should return 400 and send message when couse level is null`() {
        //given
        val courseRequest = CourseDTO(
            name = "Java",
            description = "Test",
            imgUrl = "https://www.google.com",
            courseStatus = CourseStatus.PROGRESS,
            courseLevel = null,
            userInstructor = UUID.randomUUID()
        )

        //then
        mockMvc.post("/api/v1/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(courseRequest)
        }.andExpect {
            status { isBadRequest() }
            content {
                string(
                    """
                   {"courseLevel":"must not be null"}
            """.trimIndent()
                )
            }
        }
    }


    @Test
    fun `deleteCourse should return 200 when exits course to delete`() {
        //given
        val courseId = UUID.randomUUID()

        //when
        every { courseService.findById(any()) } returns CourseDTO.fromDTO(CourseDTO(
            name = "Java",
            description = "Java Programming",
            imgUrl = "https://www.google.com",
            courseStatus = CourseStatus.PROGRESS,
            courseLevel = CourseLevel.BASIC,
            userInstructor = UUID.randomUUID()
        ))

        every { courseService.deleteCourse(any()) } returns Unit

        //then
        mockMvc.delete("/api/v1/courses/$courseId").andExpect {
            status { isOk() }
            content { string("Course delete success") }
        }
    }

    @Test
    fun `deleteCourse should return 404 when course not found`() {
        //given
        val courseId = UUID.randomUUID()

        //when
        every { courseService.findById(any()) } returns null

        //then
        mockMvc.delete("/api/v1/courses/$courseId").andExpect {
            status { isNotFound() }
            content { string("Course not found") }
        }
    }

    @Test
    fun `updateCourse should return 200 when course update`() {
        //given
        val courseId = UUID.randomUUID()
        val courseRequest = CourseDTO(
            name = "Java",
            description = "Java Programming",
            imgUrl = "https://www.google.com",
            courseStatus = CourseStatus.PROGRESS,
            courseLevel = CourseLevel.BASIC,
            userInstructor = UUID.randomUUID()
        )

        //when
        val courseReponse = CourseDTO.fromDTO(courseRequest)
        every { courseService.findById(any()) } returns courseReponse
        every { courseService.updateCourse(any(), any()) } returns courseReponse

        //then
        mockMvc.put("/api/v1/courses/$courseId") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(courseRequest)
        }.andExpect {
            status { isOk() }
            content { json(ObjectMapper().writeValueAsString(courseReponse)) }
        }
    }

    @Test
    fun `updateCoourse should return 404 when course not found`() {
        //given
        val courseId = UUID.randomUUID()

        //when
        every { courseService.findById(any()) } returns null

        //then
        mockMvc.put("/api/v1/courses/$courseId") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(CourseDTO(
                name = "Java",
                description = "Java Programming",
                imgUrl = "https://www.google.com",
                courseStatus = CourseStatus.PROGRESS,
                courseLevel = CourseLevel.BASIC,
                userInstructor = UUID.randomUUID()
            ))
        }.andExpect {
            status { isNotFound() }
            content { string("Course not found") }
        }
    }

    @Test
    fun getAllCourses() {
    }

    @Test
    fun getCourseById() {
    }
}
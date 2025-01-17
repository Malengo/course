package org.groupcreativesolution.course.service.impl

import jakarta.transaction.Transactional
import org.groupcreativesolution.course.clients.AuthUserClient
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.models.CourseUserModel
import org.groupcreativesolution.course.repositories.CourseUserRepository
import org.groupcreativesolution.course.service.CourseUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseUserServiceImpl(
    @Autowired private val courseUserRepository: CourseUserRepository,
    @Autowired private val authUserClient: AuthUserClient
) : CourseUserService {
    override fun existsByCourseAndUserId(courseModel: CourseModel, userId: UUID): Boolean {
        return courseUserRepository.existsByCourseAndUserId(courseModel, userId)
    }

    override fun save(courseUserModel: CourseUserModel) {
        courseUserRepository.save(courseUserModel)
    }

    @Transactional
    override fun saveAndSendSubscriptionUserInCourse(courseUserModel: CourseUserModel): CourseUserModel {
        val courseModel =  courseUserRepository.save(courseUserModel)
        authUserClient.postSubscriptionUserInCourse(courseModel.course?.courseId!!, courseUserModel.userId!!)
        return courseModel
    }
}
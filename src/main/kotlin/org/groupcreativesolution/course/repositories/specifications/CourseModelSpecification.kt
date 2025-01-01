package org.groupcreativesolution.course.repositories.specifications

import jakarta.persistence.criteria.*
import org.groupcreativesolution.course.enuns.CourseLevel
import org.groupcreativesolution.course.enuns.CourseStatus
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.models.CourseUserModel
import org.springframework.data.jpa.domain.Specification
import java.util.UUID

data class CourseModelSpecification(
    val courseName: String? = null,
    val courseDescription: String? = null,
    val courseStatus: CourseStatus? = null,
    val courseLevel: String? = null
) : Specification<CourseModel> {
    override fun toPredicate(
        root: Root<CourseModel>,
        query: CriteriaQuery<*>?,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val predicate: MutableList<Predicate> = ArrayList()

        courseName?.let { predicate.add(criteriaBuilder.like(root.get("courseName"), "%$it%")) }
        courseDescription?.let { predicate.add(criteriaBuilder.like(root.get("description"), "%$it%")) }
        courseStatus?.let { predicate.add(criteriaBuilder.equal(root.get<CourseStatus>("courseStatus"), it)) }
        courseLevel?.let { predicate.add(criteriaBuilder.equal(root.get<CourseLevel>("courseLevel"), it)) }

        return criteriaBuilder.and(*predicate.toTypedArray())
    }

    fun getCourseByUserId(userId: UUID): Specification<CourseModel> {
        return Specification<CourseModel> { root, query, criteriaBuilder ->
            query?.distinct(true)
            val userCourseJoin: Join<CourseModel, CourseUserModel> = root.join("courseUserModel")
            criteriaBuilder.equal(userCourseJoin.get<UUID>("userId"), userId)
        }
    }
}
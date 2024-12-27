package org.groupcreativesolution.course.repositories.specifications

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.groupcreativesolution.course.models.CourseModel
import org.groupcreativesolution.course.models.ModuleModels
import org.springframework.data.jpa.domain.Specification
import java.util.UUID

data class ModuleModelSpecification(
    val title: String? = null,
) : Specification<ModuleModels> {
    override fun toPredicate(
        root: Root<ModuleModels>,
        query: CriteriaQuery<*>?,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val predicate:MutableList<Predicate> = ArrayList()
        title?.let { predicate.add(criteriaBuilder.like(root.get("title"), "%$it%")) }

        return criteriaBuilder.and(*predicate.toTypedArray())
    }

    fun findByCourseId(courseId: UUID) : Specification<ModuleModels> {
        return Specification<ModuleModels> { root, query, criteriaBuilder ->
            query?.distinct(true)
            val course: Root<CourseModel> = query?.from(CourseModel::class.java)!!
            val coursesModules: Expression<Collection<ModuleModels>> = course.get("modules")
            criteriaBuilder.and(
                criteriaBuilder.equal(course.get<UUID>("courseId"), courseId),
                criteriaBuilder.isMember(root, coursesModules)
            )
        }
    }

}

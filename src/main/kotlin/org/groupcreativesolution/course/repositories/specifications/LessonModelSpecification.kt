package org.groupcreativesolution.course.repositories.specifications

import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.groupcreativesolution.course.models.LessonModel
import org.groupcreativesolution.course.models.ModuleModels
import org.springframework.data.jpa.domain.Specification
import java.util.*
import kotlin.collections.ArrayList

data class LessonModelSpecification(
    val title: String? = null,
) : Specification<LessonModel> {
    override fun toPredicate(
        root: Root<LessonModel>,
        query: CriteriaQuery<*>?,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val predicates: MutableList<Predicate> = ArrayList()

        title?.let { predicates.add(criteriaBuilder.like(root.get("title"), "%$it%")) }
        return criteriaBuilder.and(*predicates.toTypedArray())
    }

    fun findByModuleId(moduleId: UUID): Specification<LessonModel> {
        return Specification<LessonModel> { root, query, criteriaBuilder ->
            query?.distinct(true)
            val module: Root<ModuleModels> = query?.from(ModuleModels::class.java)!!
            val moduleLessons: Expression<Collection<LessonModel>> = module.get("lessons")
            criteriaBuilder.and(
                criteriaBuilder.equal(module.get<UUID>("moduleId"), moduleId),
                criteriaBuilder.isMember(root, moduleLessons)
            )
        }
    }


}

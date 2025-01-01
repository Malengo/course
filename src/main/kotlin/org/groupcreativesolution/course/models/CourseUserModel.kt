package org.groupcreativesolution.course.models

import jakarta.persistence.*
import kotlinx.serialization.Contextual
import java.util.*

@Entity
@Table(name = "course_users")
@kotlinx.serialization.Serializable
data class CourseUserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Contextual
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    val course: CourseModel? = null,

    @Column(nullable = false)
    @Contextual
    val userId: UUID? = null

    )
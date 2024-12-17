package org.groupcreativesolution.course.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import kotlinx.serialization.Contextual
import org.groupcreativesolution.course.enuns.CourseLevel
import org.groupcreativesolution.course.enuns.CourseStatus
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "courses")
@kotlinx.serialization.Serializable
data class CourseModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Contextual
    var courseId: UUID? = null,

    @Column(nullable = false, length = 100)
    var courseName: String? = null,

    @Column(nullable = false, length = 255)
    var description: String? = null,

    @Column
    var imageUrl: String? = null,

    @Column(nullable = false)
    var createdAt: String? = null,

    @Column(nullable = false)
    var updatedAt: String? = null,

    @Enumerated(EnumType.STRING)
    var courseStatus: CourseStatus? = null,

    @Enumerated(EnumType.STRING)
    var courseLevel: CourseLevel? = null,

    @Column(nullable = false)
    @Contextual
    var userInstructor: UUID? = null,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @Contextual
    val modules: Set<ModuleModels>? = null

)
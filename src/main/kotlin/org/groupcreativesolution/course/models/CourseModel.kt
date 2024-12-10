package org.groupcreativesolution.course.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.groupcreativesolution.course.enuns.CourseLevel
import org.groupcreativesolution.course.enuns.CourseStatus
import java.io.Serializable
import java.util.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "courses")
class CourseModel (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    var courseType: CourseLevel? = null,

    @Column(nullable = false)
    var userInstructor: UUID? = null,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course")
    val modules: Set<ModuleModels>? = null
): Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}

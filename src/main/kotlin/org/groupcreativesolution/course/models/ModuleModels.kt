package org.groupcreativesolution.course.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import kotlinx.serialization.Contextual
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.*

@Entity
@Table(name = "modules")
@JsonInclude(JsonInclude.Include.NON_NULL)
@kotlinx.serialization.Serializable
data class ModuleModels(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Contextual
    var moduleId: UUID? = null,

    @Column(nullable = false, length = 100)
    var title: String? = null,

    @Column(nullable = false, length = 255)
    var description: String? = null,

    @Column(nullable = false)
    var creationDate: String? = null,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var course: CourseModel? = null,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @Contextual
    var lessons: Set<LessonModel>? = null
)
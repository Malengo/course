package org.groupcreativesolution.course.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import kotlinx.serialization.Contextual
import java.util.*

@Entity
@Table(name = "lessons")
@kotlinx.serialization.Serializable
data class LessonModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Contextual
    var lessonId: UUID? = null,

    @Column(nullable = false, length = 150)
    var title: String? = null,

    @Column(nullable = false, length = 250)
    var description: String? = null,

    @Column(nullable = false)
    var videoUrl: String? = null,

    @Column(nullable = false)
    var creationDate: String? = null,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var module: ModuleModels? = null

)
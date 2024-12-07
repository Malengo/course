package org.groupcreativesolution.course.models

import jakarta.persistence.*
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "lessons")
class LessonModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var lessonId: UUID? = null,

    @Column(nullable = false, length = 150)
    var title: String? = null,

    @Column(nullable = false, length = 250)
    var description: String? = null,

    @Column(nullable = false)
    var videoUrl: String? = null,

    @Column(nullable = false)
    var creationDate: String? = null,

): Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
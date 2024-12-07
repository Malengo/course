package org.groupcreativesolution.course.models

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "modules")
@JsonInclude(JsonInclude.Include.NON_NULL)
class ModuleModels(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var moduleId: UUID? = null,

    @Column(nullable = false, length = 100)
    var title: String? = null,

    @Column(nullable = false, length = 255)
    var description: String? = null,

    @Column(nullable = false)
    var creationDate: String? = null,
) :
    Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
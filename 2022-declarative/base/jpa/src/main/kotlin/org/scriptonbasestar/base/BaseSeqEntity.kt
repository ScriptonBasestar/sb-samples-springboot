package org.scriptonbasestar.base

import org.springframework.data.annotation.Id
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseSeqEntity: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
        protected set
}

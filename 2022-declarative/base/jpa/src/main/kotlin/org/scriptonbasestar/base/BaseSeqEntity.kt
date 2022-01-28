package org.scriptonbasestar.base

import org.scriptonbasestar.base.embeddable.DateAtEmbeddable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseSeqEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
        protected set

    @Embedded
    var dateAt: DateAtEmbeddable = DateAtEmbeddable()
}

package org.scriptonbasestar.base

import com.google.common.base.MoreObjects
import org.scriptonbasestar.base.embeddable.DateAtEmbeddable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.UUID
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseUuidEntity(uuid: UUID = UUID.randomUUID()) : Serializable {
    @Id
    @Column(name = "uuid", length = 16, unique = true, nullable = false, updatable = false)
    // @Column(name = "uuid", length = 36, unique = true, nullable = false, updatable = false)
    // @org.hibernate.annotations.Type(type = "uuid-char")
    // @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    var uuid: UUID = uuid
        protected set

    @Embedded
    var dateAt: DateAtEmbeddable = DateAtEmbeddable()

    protected fun buildStringHelper(): MoreObjects.ToStringHelper {
        return MoreObjects.toStringHelper(this)
    }
}

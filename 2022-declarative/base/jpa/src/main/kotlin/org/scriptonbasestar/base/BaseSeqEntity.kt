package org.scriptonbasestar.base

import com.google.common.base.MoreObjects
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.scriptonbasestar.base.embeddable.DateAtEmbeddable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
abstract class BaseSeqEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
        protected set

    @Embedded
    var dateAt: DateAtEmbeddable = DateAtEmbeddable()

    protected fun buildStringHelper(): MoreObjects.ToStringHelper {
        return MoreObjects.toStringHelper(this)
    }
}

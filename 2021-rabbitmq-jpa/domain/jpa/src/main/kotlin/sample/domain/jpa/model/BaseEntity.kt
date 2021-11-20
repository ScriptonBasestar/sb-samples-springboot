package sample.domain.jpa.model

import com.google.common.base.MoreObjects
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @Column(
        nullable = false,
        insertable = true,
        updatable = false,
//        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @CreatedDate //    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(
        insertable = true,
        updatable = true,
//        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    // @CreatedDate
    @LastModifiedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime = LocalDateTime.now()

    //    @Column(nullable = false, updatable = false)
    //    @Temporal(TemporalType.TIMESTAMP)
    //    private Date createdAt;
    //
    //    //audit
    //    @Column(nullable = false)
    //    @Temporal(TemporalType.TIMESTAMP)
    //    private Date lastUpdatedAt;
    //
    //    @PrePersist
    //    protected void onCreate() {
    //        createdAt = lastUpdatedAt = new Date();
    //    }
    //
    //    @PreUpdate
    //    protected void onUpdate() {
    //        lastUpdatedAt = new Date();
    //    }
    override fun toString(): String {
        return buildStringHelper().toString()
    }

    protected fun buildStringHelper(): MoreObjects.ToStringHelper {
        return MoreObjects.toStringHelper(this)
    }
}

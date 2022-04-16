package org.scriptonbasestar.base.embeddable

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class DateAtEmbeddable {
    @Column(
        name = "created_at",
        nullable = false,
        insertable = true,
        updatable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(
        name = "updated_at",
        nullable = false,
        insertable = true,
        updatable = true,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    @CreatedDate
    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()
}

package sample.domain.model;

import com.google.common.base.MoreObjects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    @Column(nullable = false, insertable = true, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(insertable = true, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @CreatedDate
    @LastModifiedDate
    private LocalDateTime updatedAt;

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

    @Override
    public String toString() {
        return this.buildStringHelper().toString();
    }

    protected MoreObjects.ToStringHelper buildStringHelper() {
        return MoreObjects.toStringHelper(this);
    }
}

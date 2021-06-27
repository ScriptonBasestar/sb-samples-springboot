package sample.domain.model;

import com.google.common.base.MoreObjects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //audit
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = lastUpdatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = new Date();
    }

    @Override
    public String toString() {
        return this.buildStringHelper().toString();
    }

    protected MoreObjects.ToStringHelper buildStringHelper() {
        return MoreObjects.toStringHelper(this);
    }
}

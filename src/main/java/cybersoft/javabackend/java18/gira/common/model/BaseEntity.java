package cybersoft.javabackend.java18.gira.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import cybersoft.javabackend.java18.gira.common.util.DateTimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    @Column(name = Columns.ID)
    protected UUID id;

    @Version
    @Column(name = Columns.VERSION)
    protected int version;

    @CreatedBy
    @Column(name = Columns.CREATED_BY)
    protected String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATETIME_FORMAT)
    @DateTimeFormat(pattern = DateTimeUtils.DATETIME_FORMAT)
    @CreatedDate
    @Column(name = Columns.CREATED_AT)
    protected LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = Columns.LAST_MODIFIED_BY)
    protected String lastModifiedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATETIME_FORMAT)
    @DateTimeFormat(pattern = DateTimeUtils.DATETIME_FORMAT)
    @LastModifiedDate
    @Column(name = Columns.LAST_MODIFIED_AT)
    protected LocalDateTime lastModifiedAt;

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((BaseEntity) obj).id);
    }

    // inner class
    @UtilityClass
    static class Columns {
        static final String ID = "ID";
        static final String VERSION = "VERSION";
        static final String CREATED_BY = "CREATED_BY";
        static final String CREATED_AT = "CREATED_AT";
        static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
        static final String LAST_MODIFIED_AT = "LAST_MODIFIED_AT";
    }
}

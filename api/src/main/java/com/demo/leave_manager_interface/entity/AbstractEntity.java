package com.demo.leave_manager_interface.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false, nullable = false)
    protected Long id;

    @Column(nullable = false)
    private String modifiedBy;

    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(updatable = false, nullable = false)
    private String createdBy;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    protected Short isEnabled = 1;
}
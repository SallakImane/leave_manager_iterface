package com.demo.leave_manager_interface.entity;

import com.demo.leave_manager_interface.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Status extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver;

    @ManyToOne
    @JoinColumn(name = "leave_id", nullable = false)
    private Leave leave;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus approvalStatus;
}


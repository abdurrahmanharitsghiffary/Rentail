package com.abdhage.rentail.message.model;

import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.common.model.BaseEntity;
import com.abdhage.rentail.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class Message extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    @ToString.Exclude
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accommodation_id", nullable = false)
    @ToString.Exclude
    private Accommodation accommodation;

    @Lob
    private String content;

    @Column(columnDefinition = "text[]")
    private List<String> attachments;
}


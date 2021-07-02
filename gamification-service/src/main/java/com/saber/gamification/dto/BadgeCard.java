package com.saber.gamification.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "badge_cards")
@Data
public class BadgeCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long badgeId;
    private Long userId;
    private Long badgeTimeStamp;
    @Enumerated(EnumType.STRING)
    private Badge badge;

}

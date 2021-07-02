package com.saber.gamification.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class GameStats {
    private Long userId;
    private Integer score;
    private List<Badge> badges;

    public GameStats() {
        this.userId = 0L;
        this.score=0;
        this.badges = new ArrayList<>();
    }

    public GameStats(Long userId, Integer score, List<Badge> badges) {
        this.userId = userId;
        this.score = score;
        this.badges = badges;
    }

    public static GameStats emptyStats(Long userId) {
        return new GameStats(userId,0, Collections.emptyList());
    }
    public List<Badge> getBadges(){
        return Collections.unmodifiableList(badges);
    }
}

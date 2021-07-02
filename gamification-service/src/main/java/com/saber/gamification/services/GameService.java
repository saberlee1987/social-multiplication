package com.saber.gamification.services;

import com.saber.gamification.dto.GameStats;

public interface GameService {

    void newAttemptForUser(Long userId,Long attemptId,boolean correct);

    GameStats retrieveStatsForUser(Long userId);

}

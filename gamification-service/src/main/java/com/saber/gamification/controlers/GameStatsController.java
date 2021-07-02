package com.saber.gamification.controlers;

import com.saber.gamification.dto.GameStats;
import com.saber.gamification.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats")
public class GameStatsController {

    private GameService gameService;

    public GameStatsController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<GameStats> getStatsForUser(@RequestParam(value = "userId")Long userId){

        GameStats gameStats = this.gameService.retrieveStatsForUser(userId);

        return ResponseEntity.ok(gameStats);
    }
}

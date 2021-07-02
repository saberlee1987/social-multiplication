package com.saber.gamification.controlers;

import com.saber.gamification.dto.LeaderBoardRow;
import com.saber.gamification.dto.LeaderBoardRowResponse;
import com.saber.gamification.services.LeaderBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/leaders")
public class LeaderBoardController {

    private LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping
    public ResponseEntity<LeaderBoardRowResponse> getLeaderBoard(){
        List<LeaderBoardRow> leaderBoardRows = this.leaderBoardService.getCurrentLeaderBoard();
        LeaderBoardRowResponse leaderBoardRowResponse = new LeaderBoardRowResponse();
        leaderBoardRowResponse.setLeaderBoardRows(leaderBoardRows);
        return ResponseEntity.ok(leaderBoardRowResponse);
    }
}

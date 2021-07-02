package com.saber.gamification.repositores;

import com.saber.gamification.dto.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BadgeCardRepository extends JpaRepository<BadgeCard,Long> {

    List<BadgeCard> findByUserIdOrderByBadgeTimeStampDesc(Long userId);
}

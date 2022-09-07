package me.func.ebisu.repository;

import me.func.ebisu.entity.RewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author func 06.09.2022
 * @project cases
 */
@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, Long> {
}

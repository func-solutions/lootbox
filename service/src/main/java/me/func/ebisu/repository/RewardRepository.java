package me.func.ebisu.repository;

import me.func.ebisu.entity.RewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, Long> {
}

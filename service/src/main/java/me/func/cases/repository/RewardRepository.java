package me.func.cases.repository;

import me.func.cases.entity.RewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author func 06.09.2022
 * @project cases
 */
@Component
@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, Long> {

}

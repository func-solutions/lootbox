package me.func.ebisu.service;

import me.func.ebisu.entity.RewardEntity;
import me.func.ebisu.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author func 06.09.2022
 * @project cases
 */
@Service
public class RewardService {

	private RewardRepository rewardRepository;

	public RewardService(@Autowired RewardRepository rewardRepository) {
		this.rewardRepository = rewardRepository;
	}

	@Transactional
	public void addReward(RewardEntity reward) {
		rewardRepository.save(reward);
	}

}

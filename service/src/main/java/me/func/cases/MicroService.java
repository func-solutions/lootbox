package me.func.cases;

import me.func.cases.entity.RewardEntity;
import me.func.cases.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Service
public class MicroService {

	private final RewardRepository rewardRepository;

	public MicroService(@Autowired RewardRepository rewardRepository) {
		this.rewardRepository = rewardRepository;
	}

	public void run() {
		rewardRepository.save(new RewardEntity());
	}
}

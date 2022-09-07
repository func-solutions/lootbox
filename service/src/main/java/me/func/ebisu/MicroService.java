package me.func.ebisu;

import me.func.ebisu.data.RewardType;
import me.func.ebisu.data.RewardWrapper;
import me.func.ebisu.entity.RewardEntity;
import me.func.ebisu.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Component
public class MicroService {

	private final RewardRepository rewardRepository;

	public MicroService(@Autowired RewardRepository rewardRepository) {
		this.rewardRepository = rewardRepository;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void run() {
		rewardRepository.save(new RewardEntity(2L, RewardType.GRAFFITI, new RewardWrapper(
				UUID.randomUUID(), ""
		)));
	}
}

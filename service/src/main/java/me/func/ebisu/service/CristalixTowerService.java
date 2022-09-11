package me.func.ebisu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.entity.PackCaseRelationEntity;
import me.func.ebisu.entity.PackEntity;
import me.func.ebisu.entity.RewardEntity;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.repository.PackCaseRelationRepository;
import me.func.ebisu.repository.PackRepository;
import me.func.ebisu.repository.RewardRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CristalixTowerService {

	private final BoxRepository boxRepository;
	private final PackRepository packRepository;
	private final RewardRepository rewardRepository;
	private final PackCaseRelationRepository relationRepository;

	@Transactional
	public void addBoxes(List<BoxEntity> boxes) {
		boxRepository.saveAll(boxes);
	}

	@Transactional
	public void addPacks(List<PackEntity> packs) {
		packRepository.saveAll(packs);
	}

	@Transactional
	public void addRewards(List<RewardEntity> rewards) {
		rewardRepository.saveAll(rewards);
	}

	@Transactional
	public void updatePackBoxRelation(long packId, long boxId, long count) {
		val boxOptional = boxRepository.findById(packId);
		if (boxOptional.isEmpty()) return;
		BoxEntity box = boxOptional.get();

		val packOptional = packRepository.findById(boxId);
		if (packOptional.isEmpty()) return;
		PackEntity pack = packOptional.get();

		val relationOptional = relationRepository.findByBoxAndPack(box, pack);
		if (relationOptional.isPresent()) {
			PackCaseRelationEntity relation = relationOptional.get();
			relation.setAmount(count);
			relationRepository.save(relation);
		} else {
			relationRepository.save(new PackCaseRelationEntity(pack, box, count));
		}
	}
}

package me.func.ebisu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.entity.EventEntity;
import me.func.ebisu.entity.PackEntity;
import me.func.ebisu.model.RewardType;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.repository.EventRepository;
import me.func.ebisu.repository.PackCaseRelationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.stream.Collectors;

@Profile("prod")
@Slf4j
@Service
@RequiredArgsConstructor
public class LootboxService {

	private final PackCaseRelationRepository relationRepository;
	private final EventRepository eventRepository;

	@Transactional
	public PackEntity roll(UUID player, BoxEntity box) {

		log.info("Start generating drop for {}, data: {}", player, box);

		// Смотрим что игрок уже выбил, создаем списочек,
		val alreadyDropped = eventRepository.getByBoxAndPlayerUuid(box, player).stream()
				.map(EventEntity::getPack)
				.collect(Collectors.toSet());

		// Передаем его в relation и выбираем случайный не из этих
		val randomDrop = relationRepository.findRandomRelation(box, alreadyDropped);

		if (randomDrop.isEmpty())
			return null;

		// Вычитаем награду и возвращаем ее
		val drop = randomDrop.get();

		drop.setAmount(drop.getAmount() - 1);
		relationRepository.save(drop);

		log.info("Generated drop for {}, data: {}", player, drop);

		// Сохраняем ивент в одной транзакции
		eventRepository.save(new EventEntity(new Timestamp(System.currentTimeMillis()), drop.getPack(), box, player));

		log.info("New event saved!");

		return drop.getPack();
	}

}

package me.func.ebisu.service.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import me.func.ebisu.entity.button.ButtonMirror;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.repository.EventRepository;
import me.func.ebisu.repository.RewardRepository;
import me.func.protocol.menu.SelectionModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author func 11.09.2022
 * @project ebisu
 */
@Component
@RequiredArgsConstructor
public class ReportService {

	@Getter
	private final SelectionModel logs = model(7, 1, "Последние выпадения");
	@Getter
	private final SelectionModel list = model(3, 2, "Список кейсов");
	@Getter
	private final SelectionModel rewards = model(5, 2, "Возможные награды");

	private final EventRepository eventRepository;
	private final BoxRepository boxRepository;
	private final RewardRepository rewardRepository;

	@Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
	public void everyMinute() {
		chain(logs, eventRepository, PageRequest.of(10, logs.pageSize(), Sort.by("timestamp")));
		chain(list, boxRepository, PageRequest.of(1, list.pageSize(), Sort.by("price")));
		chain(rewards, rewardRepository, PageRequest.of(5, rewards.pageSize()));
	}

	@Async
	void chain(SelectionModel replacement, JpaRepository<? extends ButtonMirror, Long> repository, PageRequest request) {
		val data = repository.findAll(request)
				.stream()
				.map(ButtonMirror::asButton)
				.toList();
		synchronized (this) {
			replacement.setData(data);
		}
	}

	private SelectionModel model(int rows, int columns, String title) {
		return SelectionModel.builder().title(title).rows(rows).columns(columns).build();
	}
}

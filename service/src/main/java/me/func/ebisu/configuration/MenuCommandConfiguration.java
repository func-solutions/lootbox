package me.func.ebisu.configuration;

import lombok.extern.slf4j.Slf4j;
import me.func.ebisu.model.MenuCommand;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.repository.EventRepository;
import me.func.ebisu.repository.RewardRepository;
import me.func.protocol.menu.Button;
import me.func.protocol.menu.SelectionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Configuration
public class MenuCommandConfiguration {

	@Bean("box-logs")
	public MenuCommand boxLogs(EventRepository eventRepository) {

		return menuCommand(7, 1, "Последние выпадения", eventRepository, 10, Sort.by("timestamp"),
				(builder, event) -> builder
						.title("Запись номер " + event.getId())
						.description("Игрок выбил " + event.getPack().getRare().name())
						.texture(event.getBox().getTexture())
		);
	}

	@Bean("box-list")
	public MenuCommand boxList(BoxRepository boxRepository) {

		return menuCommand(3, 2, "Список кейсов", boxRepository, 1, Sort.by("price"),
				(builder, box) -> builder
						.command("box-buy")
						.title(box.getTitle())
						.vault(box.getCurrency())
						.price((int) box.getPrice().doubleValue())
						.texture(box.getTexture())
		);
	}

	@Bean("box-rewards")
	public MenuCommand boxRewards(RewardRepository rewardRepository) {

		return menuCommand(5, 2, "Возможные награды", rewardRepository, 5, Sort.unsorted(),
				(builder, reward) -> builder
						.title("Награда номер " + reward.getId())
						.texture("")
		);
	}

	public <T> MenuCommand menuCommand(int rows, int columns, String title, JpaRepository<T, Long> repository, int pageCount, Sort sort, BiConsumer<Button.Builder, T> creator) {

		SelectionModel model = SelectionModel.builder().title(title).rows(rows).columns(columns).build();

		Supplier<List<Button>> supplier = () -> repository.findAll(PageRequest.of(0, model.pageSize() * pageCount, sort))
				.stream()
				.map(object -> {
					Button.Builder builder = Button.builder();
					creator.accept(builder, object);
					return builder.build();
				}).toList();

		return new MenuCommand(model, supplier);
	}

}

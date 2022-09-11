package me.func.ebisu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.func.ebisu.network.AddBoxesPackage;
import me.func.ebisu.network.AddPacksPackage;
import me.func.ebisu.network.AddRewardsPackage;
import me.func.ebisu.network.SetPackCaseRelationPackage;
import me.func.ebisu.service.CristalixTowerService;
import org.springframework.stereotype.Component;
import ru.cristalix.core.network.Capability;
import ru.cristalix.core.network.CorePackage;
import ru.cristalix.core.network.ISocketClient;
import ru.cristalix.core.realm.RealmId;

import javax.annotation.PostConstruct;
import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class CristalixTowerController {

	private final ISocketClient client;
	private final CristalixTowerService towerService;

	@PostConstruct
	public void run() {

		registerListener(AddBoxesPackage.class, (realmId, pckg) ->
				towerService.addBoxes(pckg.getBoxes()));

		registerListener(AddPacksPackage.class, (realmId, pckg) ->
				towerService.addPacks(pckg.getPacks()));

		registerListener(AddRewardsPackage.class, (realmId, pckg) ->
				towerService.addRewards(pckg.getRewards()));

		registerListener(SetPackCaseRelationPackage.class, (realmId, pckg) ->
				towerService.updatePackBoxRelation(pckg.getPackId(), pckg.getBoxId(), pckg.getCount()));

	}

	private <T extends CorePackage> void registerListener(Class<T> type, BiConsumer<RealmId, T> handler) {

		client.registerCapability(Capability.builder()
				.className(type.getSimpleName())
				.notification(true)
				.build()
		);

		client.addListener(type, (realmId, t) -> {
			log.debug("Got package {} from realm {}", t.getClass().getName(), realmId.getRealmName());
			try {
				handler.accept(realmId, t);
			} catch (Exception ex) {
				log.error("Error handling " + t.getClass().getName() + " from " + realmId.getRealmName() + ": " + t, ex);
				throw ex;
			}
		});

	}

}

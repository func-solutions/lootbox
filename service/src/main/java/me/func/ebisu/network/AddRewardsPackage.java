package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.entity.RewardEntity;
import ru.cristalix.core.network.CorePackage;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AddRewardsPackage extends CorePackage {

	private final List<RewardEntity> rewards;

}

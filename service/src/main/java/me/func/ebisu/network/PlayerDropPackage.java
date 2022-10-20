package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.entity.PackEntity;
import ru.cristalix.core.network.CorePackage;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class PlayerDropPackage extends CorePackage {

	private final UUID player;
	private final PackEntity drop;

}

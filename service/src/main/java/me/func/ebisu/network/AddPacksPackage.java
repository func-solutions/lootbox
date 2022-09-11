package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.entity.PackEntity;
import ru.cristalix.core.network.CorePackage;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AddPacksPackage extends CorePackage {

	private final List<PackEntity> packs;

}

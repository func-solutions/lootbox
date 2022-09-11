package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.entity.BoxEntity;
import ru.cristalix.core.network.CorePackage;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AddBoxesPackage extends CorePackage {

	private final List<BoxEntity> boxes;

}

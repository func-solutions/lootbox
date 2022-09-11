package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cristalix.core.network.CorePackage;

@Getter
@RequiredArgsConstructor
public class SetPackCaseRelationPackage extends CorePackage {

	private final long packId;
	private final long boxId;
	private final long count;

}

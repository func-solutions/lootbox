package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cristalix.core.network.CorePackage;

@Getter
@RequiredArgsConstructor
public class BroadcastMessagePackage extends CorePackage {

	private final String message;

}

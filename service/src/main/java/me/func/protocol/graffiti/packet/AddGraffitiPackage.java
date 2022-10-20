package me.func.protocol.graffiti.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.cristalix.core.network.CorePackage;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddGraffitiPackage extends CorePackage {

	private UUID playerUuid; // request
	private UUID graffitiUUID; // request
	private int amount; // request

}

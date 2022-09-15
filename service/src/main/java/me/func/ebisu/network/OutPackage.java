package me.func.ebisu.network;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.cristalix.core.network.CorePackage;

@EqualsAndHashCode(callSuper = true)
@Data
public class OutPackage extends CorePackage {

	private String errorMessage;
	private boolean success;

}

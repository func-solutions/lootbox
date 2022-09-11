package me.func.ebisu.configuration;

import lombok.Data;
import me.func.protocol.menu.Button;
import me.func.protocol.menu.SelectionModel;

import java.util.List;
import java.util.function.Supplier;

@Data
public class MenuCommand {

	private final SelectionModel model;
	private final Supplier<List<Button>> buttonSupplier;
	private String cachedResponse;

}

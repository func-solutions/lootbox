package me.func.ebisu.data;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author func 05.09.2022
 * @project cases
 */
@AllArgsConstructor
public class RewardWrapper implements Serializable {

	private UUID uuid;
	private String data;

}

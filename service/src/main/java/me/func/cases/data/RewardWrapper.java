package me.func.cases.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Data
public class RewardWrapper implements Serializable {

	private UUID uuid;
	private String data;

}

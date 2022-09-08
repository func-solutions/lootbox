package me.func.ebisu.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author func 08.09.2022
 * @project ebisu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu implements Serializable {

	private String title;
	private int rows;
	private int columns;
	private List<Button> storage;

}

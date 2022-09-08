package me.func.ebisu.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class Button implements Serializable {

	private String title = "Название";
	private String texture = "Текстура";
	private String hover = null;
	private Long price = 0L;

}

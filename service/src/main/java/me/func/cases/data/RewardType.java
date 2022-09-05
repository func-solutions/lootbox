package me.func.cases.data;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RewardType {

	PERSONALIZATION("Персонализация"),
	GRAFFITI("Граффити"),
	STICKER("Стикеры"),
	BONUSES("Бонусы"),
	SKIN("Скины"),
	VAULT("Валюта"),;

	private String title;

}

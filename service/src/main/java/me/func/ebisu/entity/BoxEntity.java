package me.func.ebisu.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "box")
public class BoxEntity {
	@Id
	@GeneratedValue
	@Column(name = "box_id")
	private Long id;

	@Column(nullable = false)
	private Double price;

	private String title;

	private String currency;

	private String status;
}

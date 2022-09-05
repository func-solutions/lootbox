package me.func.cases.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "case")
public class CaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "case_id")
	private Long id;

	private Double price;

	private String title;

	private String currency;
}

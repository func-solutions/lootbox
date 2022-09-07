package me.func.ebisu.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "pack_case_relation")
public class PackCaseRelationEntity {

	@OneToOne(targetEntity = PackEntity.class)
	private PackEntity pack;

	@OneToOne(targetEntity = BoxEntity.class)
	private BoxEntity box;

	private Long amount;

	@Id
	@GeneratedValue
	private Long id;

}

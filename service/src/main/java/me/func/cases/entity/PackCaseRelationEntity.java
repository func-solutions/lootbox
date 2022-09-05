package me.func.cases.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "pack_case_relation")
public class PackCaseRelationEntity {

	@Column(name = "pack_id")
	@OneToOne(mappedBy = "pack", targetEntity = PackEntity.class)
	private PackEntity packEntity;

	@Column(name = "case_id")
	@OneToOne(mappedBy = "case", targetEntity = CaseEntity.class)
	private CaseEntity caseEntity;

	private Long amount;

}

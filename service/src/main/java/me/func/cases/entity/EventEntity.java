package me.func.cases.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "event")
public class EventEntity {
	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private Long id;

	@Basic
	private Timestamp timestamp;

	@OneToOne(mappedBy = "pack", targetEntity = PackEntity.class)
	@Column(name = "pack")
	private PackEntity packEntity;

	@OneToOne(mappedBy = "case", targetEntity = CaseEntity.class)
	@Column(name = "case")
	private CaseEntity caseEntity;

	private UUID playerUuid;

}

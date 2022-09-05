package me.func.cases.entity;

import lombok.Data;

import javax.persistence.*;
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

	@OneToOne(targetEntity = PackEntity.class)
	private PackEntity pack;

	@OneToOne(targetEntity = BoxEntity.class)
	private BoxEntity box;

	private UUID playerUuid;

}

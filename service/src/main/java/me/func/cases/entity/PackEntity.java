package me.func.cases.entity;

import jakarta.persistence.*;
import lombok.Data;
import me.func.cases.data.RareType;

import java.util.Set;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "pack")
public class PackEntity {
	@Id
	@GeneratedValue
	@Column(name = "pack_id")
	private Long id;
	private Double chance;
	private RareType rare;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "reward", targetEntity = RewardEntity.class)
	private Set<RewardEntity> rewards;
}

package me.func.ebisu.entity;

import lombok.Builder;
import lombok.Data;
import me.func.ebisu.data.RareType;

import javax.persistence.*;
import java.util.Set;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "pack")
@Builder
public class PackEntity {
	@Id
	@GeneratedValue
	@Column(name = "pack_id")
	private Long id;
	private Double chance;
	private RareType rare;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(targetEntity = RewardEntity.class)
	private Set<RewardEntity> rewards;
}

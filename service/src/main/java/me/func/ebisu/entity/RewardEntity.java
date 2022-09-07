package me.func.ebisu.entity;

import lombok.*;
import me.func.ebisu.data.RewardType;
import me.func.ebisu.data.RewardWrapper;

import javax.persistence.*;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "reward")
@AllArgsConstructor
@NoArgsConstructor
public class RewardEntity {
	@Id
	@GeneratedValue
	@Column(name = "reward_id")
	private Long id;

	@Column(nullable = false)
	private RewardType type;

	@Column(nullable = false)
	private RewardWrapper reward;
}

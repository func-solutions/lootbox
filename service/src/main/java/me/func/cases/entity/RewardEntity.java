package me.func.cases.entity;

import lombok.*;
import me.func.cases.data.RewardType;
import me.func.cases.data.RewardWrapper;

import javax.persistence.*;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Data
@Table(name = "reward")
public class RewardEntity {
	@Id
	@GeneratedValue
	@Column(name = "reward_id")
	private Long id;

	private RewardType type;

	private RewardWrapper reward;
}

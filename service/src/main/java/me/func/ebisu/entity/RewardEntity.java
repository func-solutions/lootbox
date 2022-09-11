package me.func.ebisu.entity;

import lombok.*;
import me.func.ebisu.model.RewardType;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "reward")
@AllArgsConstructor
public class RewardEntity {
	@Id
	@GeneratedValue
	@Column(name = "reward_id")
	private Long id;

	@Column(name = "reward_type", nullable = false)
	private RewardType type;

	@Column(name = "reward_item_id", nullable = false)
	private UUID rewardItemId;

	@Column(name = "reward_data", nullable = false)
	private String rewardData;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		RewardEntity that = (RewardEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

package me.func.ebisu.entity;

import lombok.*;
import me.func.ebisu.data.Button;
import me.func.ebisu.data.RewardType;
import me.func.ebisu.data.RewardWrapper;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

package revolution.entities;

import java.util.ArrayList;
import java.util.List;

import revolution.enums.Restriction;

public class ChoiceInfo {
	private List<Reward> rewards;
	private Restriction restriction;
	
	public ChoiceInfo() {
		rewards = new ArrayList<>();
		restriction = Restriction.NoRestriction;
	}
	
	
	public ChoiceInfo(Restriction restriction) {
		rewards = new ArrayList<>();
		this.restriction = restriction;
	}


	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	public Restriction getRestriction() {
		return restriction;
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}
	
	public ChoiceInfo addReward(Reward reward) {
		this.rewards.add(reward);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((restriction == null) ? 0 : restriction.hashCode());
		result = prime * result + ((rewards == null) ? 0 : rewards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChoiceInfo))
			return false;
		ChoiceInfo other = (ChoiceInfo) obj;
		if (restriction != other.restriction)
			return false;
		if (rewards == null) {
			if (other.rewards != null)
				return false;
		} else if (!rewards.equals(other.rewards))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChoiceInfo [rewards=" + rewards + ", restriction="
				+ restriction + "]";
	}



}

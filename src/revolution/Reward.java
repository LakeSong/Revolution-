package revolution;

public class Reward {
	private int howMuch;
	private RewardType typeReward;
	private AreaName areaName;
	
	
	public Reward(int howMuch, RewardType typeReward, AreaName areaName) {
		super();
		this.howMuch = howMuch;
		this.typeReward = typeReward;
		this.areaName = areaName;
	}

	public int getHowMuch() {
		return howMuch;
	}

	public void setHowMuch(int howMuch) {
		this.howMuch = howMuch;
	}

	public RewardType getTypeReward() {
		return typeReward;
	}

	public void setTypeReward(RewardType typeReward) {
		this.typeReward = typeReward;
	}

	public AreaName getAreaName() {
		return areaName;
	}

	public void setAreaName(AreaName areaName) {
		this.areaName = areaName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((areaName == null) ? 0 : areaName.hashCode());
		result = prime * result + howMuch;
		result = prime * result
				+ ((typeReward == null) ? 0 : typeReward.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reward))
			return false;
		Reward other = (Reward) obj;
		if (areaName != other.areaName)
			return false;
		if (howMuch != other.howMuch)
			return false;
		if (typeReward != other.typeReward)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reward [howMuch=" + howMuch + ", typeReward=" + typeReward
				+ ", areaName=" + areaName + "]";
	}
}

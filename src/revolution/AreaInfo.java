package revolution;

public class AreaInfo {
	private int maxCubes;
	private int points; // area's value in points

	public int getMaxCubes() {
		return maxCubes;
	}

	public void setMaxCubes(int maxCubes) {
		this.maxCubes = maxCubes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxCubes;
		result = prime * result + points;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AreaInfo))
			return false;
		AreaInfo other = (AreaInfo) obj;
		if (maxCubes != other.maxCubes)
			return false;
		if (points != other.points)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AreaInfo [maxCubes=" + maxCubes + ", points=" + points + "]";
	}

}

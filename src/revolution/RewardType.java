package revolution;

public enum RewardType {
	Gold,
	Blackmail,
	Force,
	Support,
	Influence;

	public TokenType toTokenType() throws RevolutionGameException {
		switch (this) {
		case Gold:
			return TokenType.Gold;
		case Blackmail:
			return TokenType.Blackmail;
		case Force:
			return TokenType.Force;
		default:
			throw new RevolutionGameException("Call toTokenType on a none Token reward.");
		}		
	}
}

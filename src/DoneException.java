public class DoneException extends Exception {
	private static final long serialVersionUID = 1L;
	public State winner;
	
	public DoneException(State winner) {
		this.winner = winner;
	}
}

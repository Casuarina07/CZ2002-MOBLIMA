package model;

public class Seat {
	private int seatId;
	private boolean isOccupied;

	public Seat(int seatId, boolean isOccupied) {
		super();
		this.seatId = seatId;
		this.isOccupied = isOccupied;
	}

	public int getSeatId() {
		return seatId;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public void occupySeat() throws SeatOccupiedException {
		if (isOccupied)
			throw new SeatOccupiedException();
		else
			this.isOccupied = true;

	}

	public class SeatOccupiedException extends Exception {
		private static final long serialVersionUID = 1L;

		public SeatOccupiedException() {
		}

		@Override
		public String getMessage() {
			return "The seat is occupied!";
		}
	}

	public class SeatFullException extends Exception {
		private static final long serialVersionUID = 1L;

		@Override
		public String getMessage() {
			return "The seats are full!";
		}
	}
}
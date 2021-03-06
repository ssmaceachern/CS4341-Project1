package player;

import java.util.Comparator;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Opponent {

	private int xLocation, yLocation, streak, potentialStreak, threatValue;
	private boolean sign;

	public Opponent(int x, int y, int streak, int potentialStreak, boolean sign) {
		this.setX(x);
		this.setY(y);
		this.setStreak(streak);
		this.setPotentialStreak(potentialStreak);
		this.setSign(sign);
		setThreat(y % 2 + 1);
	}

	
	@SuppressWarnings("rawtypes")
	public static Comparator getComparator() {
		return new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {

				Opponent t1 = (Opponent) o1;
				Opponent t2 = (Opponent) o2;

				if (t1.getX() > t2.getX())
					return 1; // greater
				if (t1.getX() == t2.getX()) {
					if (t1.getY() > t2.getY())
						return 1; // greater
					if (t1.getY() == t2.getY())
						return 0; // equal, impossible
					if (t1.getY() < t2.getY())
						return -1;
				}
				if (t1.getX() < t2.getX())
					return -1;

				return 0;
			}
		};
	}

	/**
	 * @return the sign
	 */
	public boolean isSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(boolean sign) {
		this.sign = sign;
	}

	/**
	 * @return the streak
	 */
	public int getStreak() {
		return streak;
	}

	/**
	 * @param streak
	 *            the streak to set
	 */
	public void setStreak(int streak) {
		this.streak = streak;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return xLocation;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.xLocation = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return yLocation;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.yLocation = y;
	}

	/**
	 * @return the potentialStreak
	 */
	public int getPotentialStreak() {
		return potentialStreak;
	}

	/**
	 * @param potentialStreak
	 *            the potentialStreak to set
	 */
	public void setPotentialStreak(int potentialStreak) {
		this.potentialStreak = potentialStreak;
	}

	/**
	 * @return the threat
	 */
	public int getThreat() {
		return threatValue;
	}

	/**
	 * @param threat
	 *            the threat to set
	 */
	public void setThreat(int threat) {
		this.threatValue = threat;
	}
}

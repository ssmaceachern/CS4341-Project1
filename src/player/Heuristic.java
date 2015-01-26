package player;

import java.util.ArrayList;
import java.util.Collections;

import game.Board;
import game.Piece;

/**
 * 
 * @author Zach Arnold and Sean MacEachern
 *
 */
public class Heuristic {
	
	/**
	 * 
	 * @param board
	 * @return
	 */
	public int evaluate2(Board board) {
		int points = 0;
		int eval = 0;
		int row;
		int rows = board.getWidth();
		int sq;
		int column;
		int cols = board.getHeight();
		int posn[][] = new int[rows][cols];
		for (int sr = 0; sr < rows; sr++) {
			for(int sc = 0; sc < cols; sc++){
				if(board.getPieces()[sr][sc].isMine())
					posn[sr][sc] = 1;
				else if(!board.getPieces()[sr][sc].isMine()){
					posn[sr][sc] = 2;
				}else{
					posn[sr][sc] = 0;
				}
			}
		}
		int type = 0;

		for (row = 0; row < rows; row++) {
			for (column = 0; column < cols - 3; column++) {
				if (posn[row][column] != 0
						&& posn[row][column] == posn[row][column + 1]
						&& posn[row][column] == posn[row][column + 2]
						&& posn[row][column] == posn[row][column + 3]) {
					eval = -1000;
				}
			}
		}

		// check for a vertical win
		for (row = 0; row < rows - 3; row++) {
			for (column = 0; column < cols; column++) {
				if (posn[row][column] != 0
						&& posn[row][column] == posn[row + 1][column]
						&& posn[row][column] == posn[row + 2][column]
						&& posn[row][column] == posn[row + 3][column]) {
					eval = -1000;
				}
			}
		}

		// check for a diagonal win (positive slope)
		for (row = 0; row < rows - 3; row++) {
			for (column = 0; column < cols - 3; column++) {
				if (posn[row][column] != 0
						&& posn[row][column] == posn[row + 1][column + 1]
						&& posn[row][column] == posn[row + 2][column + 2]
						&& posn[row][column] == posn[row + 3][column + 3]) {
					eval = -1000;
				}
			}
		}

		// check for a diagonal win (negative slope)
		for (row = rows - 3; row < rows; row++) {
			for (column = 0; column < cols - 3; column++) {
				if (posn[row][column] != 0
						&& posn[row][column] == posn[row - 1][column + 1]
						&& posn[row][column] == posn[row - 2][column + 2]
						&& posn[row][column] == posn[row - 3][column + 3]) {
					eval = -1000;
				}
			}
		}
		int points1 = 0;
		int points2 = 0;

		// _XXX_ from row 4onwards.
		for (row = rows - 2; row >= 1; row--) {
			for (column = 0; column < cols - 4; column++) {
				if (posn[row][column] == 0 && posn[row][column + 1] == type
						&& posn[row][column + 2] == type
						&& posn[row][column + 3] == type
						&& posn[row][column + 4] == 0) {
					if (row % 2 != 0) {
						points1 = points1 + 10;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
			}
		}

		for (row = 0; row < rows - 4; row++) {
			for (column = 0; column < cols - 6; column++) {
				if (posn[row][column] == 0 && posn[row + 1][column + 1] == type
						&& posn[row + 2][column + 2] == type
						&& posn[row + 3][column + 3] == type
						&& posn[row + 4][column + 4] == 0) {
					if (row % 2 != 0) {
						points1 = points1 + 10;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
			}
		}

		for (row = 0; row < rows - 3; row++) {
			for (column = 0; column < cols - 5; column++) {
				if (posn[row][column] == type && posn[row + 1][column + 1] == 0
						&& posn[row + 2][column + 2] == type
						&& posn[row + 3][column + 3] == type) {
					if (row % 2 != 0) {
						points1 = points1 + 10;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
				if (posn[row][column] == type
						&& posn[row + 1][column + 1] == type
						&& posn[row + 2][column + 2] == 0
						&& posn[row + 3][column + 3] == type) {
					if (row % 2 != 0) {
						points1 = points1 + 10;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
				if (posn[row][column] == type
						&& posn[row + 1][column + 1] == type
						&& posn[row + 2][column + 2] == type
						&& posn[row + 3][column + 3] == 0) {
					if (row % 2 != 0) {
						points1 = points1 + 10;
					}
					if (type == 2) {
						points2 = -1 * points1;
					}
				}
			}
			if (posn[row][column] == 0 && posn[row + 1][column + 1] == type
					&& posn[row + 2][column + 2] == type
					&& posn[row + 3][column + 3] == type) {
				if (row % 2 != 0) {
					points1 = points1 + 10;
				}
				if (type == 2) {
					points2 = -1 * points1;
				}
			}
		}

		for (row = rows - 2; row >= 1; row--) {
			for (column = 0; column < cols - 4; column++) {
				// _XXX
				if (posn[row][column] == 0 && posn[row][column + 1] == type
						&& posn[row][column + 2] == type
						&& posn[row][column + 3] == type) {
					if (row % 2 != 0) {
						points1 = points1 + 1;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
				// XXX_
				if (posn[row][column] == type && posn[row][column + 1] == type
						&& posn[row][column + 2] == type
						&& posn[row][column + 3] == 0) {
					if (row % 2 != 0) {
						points1 = points1 + 1;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
				// XX_X
				if (posn[row][column] == type && posn[row][column + 1] == type
						&& posn[row][column + 2] == 0
						&& posn[row][column + 3] == type) {
					if (row % 2 != 0) {
						points1 = points1 + 1;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
				// X_XX
				if (posn[row][column] == type && posn[row][column + 1] == 0
						&& posn[row][column + 2] == 0
						&& posn[row][column + 3] == type) {
					if (row % 2 != 0) {
						points1 = points1 + 1;
					}
					if (type == 1) {
						points2 = -1 * points1;
					}
				}
			}
		}
		 
		 points = points1 + points2; 
		if (eval == -1000) {
			return eval;
		} else {
			return points;
		}

	}

	@SuppressWarnings("unchecked")
	/**
	 * This method evaluates the state of the board using a Heuristic that analyzes the opposing team's positions as threats and assigns values based on their positions and potential series of moves
	 * @param board - The board to evaluate
	 * @return - An integer value for the usefulness of this board state
	 */
	public int evaluate(Board board) {

		// return a low score if the board is non-existant
		if (board == null) {
			return 0;
		}

		// Evaluation function variable declarations
		ArrayList<Opponent> opposingThreats = new ArrayList<Opponent>();
		Piece[][] pieces = board.getPieces();
		boolean sign = true;
		int numOfConsecutivePieces = 0;
		int potentialConsecutivePieces = 0;
		boolean previousEmpty = false;

		// The final score (currently 0,) to return as the board state value
		int total = 0;

		// default values for empty slots on the board
		int emptyX = -1;
		int emptyY = -1;

		// Check horizontal threats
		boolean noPieceYet = true;
		for (int y = 0; y < board.getHeight(); y++) {
			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			for (int x = 0; x < board.getWidth(); x++) {
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// Check diagonal threats on bottom half of board
		int y = 0;
		for (int i = 0; i < board.getWidth(); i++) {

			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			y = 0;

			for (int x = i; x < board.getWidth(); x++) {
				if (y >= board.getHeight()) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;

				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				y++; // move up too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// Check diagonal threats on top half of board
		int x = 0;
		for (int i = 0; i < board.getHeight(); i++) {

			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			x = 0;

			for (y = i; y < board.getHeight(); y++) {
				if (x >= board.getWidth()) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				x++; // move across, too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// downard diagonals
		// Check diagonal threats on bottom half of board
		for (int i = board.getWidth() - 1; i >= 0; i--) {
			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			y = 0;

			for (x = i; x >= 0; x--) {
				if (y >= board.getHeight()) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				y++; // move up too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}
		// downard diagonals
		// Check diagonal threats on bottom half
		for (int i = 0; i < board.getHeight(); i++) {

			numOfConsecutivePieces = 0;
			potentialConsecutivePieces = 0;
			noPieceYet = true;
			x = board.getWidth() - 1;

			for (y = i; y < board.getHeight(); y++) {
				if (x < 0) // hit the end of this diagonal
					break;
				if (pieces[x][y] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x;
						emptyY = y;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (noPieceYet) {
						sign = pieces[x][y].isMine();
						numOfConsecutivePieces++;
					} else if (sign != pieces[x][y].isMine()) { // broke the
																// streak
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) { // remember
							// the
							// threat
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x][y].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
					noPieceYet = false;
				}

				x--; // move across, too
			}

			if ((previousEmpty || numOfConsecutivePieces == 1) && !noPieceYet) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) { // remember
					// the
					// threat
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign)); // I can look at even/odd
					// later
				}
			}
		}

		// Check vertical threats (as they are the worst.)
		for (int x1 = 0; x1 < board.getWidth(); x1++) {

			// check to see if the column is empty...if it is, we can skip it
			if (pieces[x1][0] == null)
				continue;

			// Set up variables to loop over the spaces
			numOfConsecutivePieces = 1;
			potentialConsecutivePieces = 0;
			sign = pieces[x1][0].isMine();
			previousEmpty = false;

			for (int y1 = 1; y1 < board.getHeight(); y1++) {
				if (pieces[x1][y1] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x1;
						emptyY = y1;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (sign != pieces[x1][y1].isMine()) // we've broken the
															// streak
					{
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) {
							// this is bad!! Store the threat in the
							// opposingThreats object
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x1][y1].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
				}
			}

			if (previousEmpty || numOfConsecutivePieces == 1) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) {
					// this is bad!! Store the threat in the opposingThreats
					// object
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign));
				}
			}
		}
		// Check for a good pop-out condition
		for (int x1 = 0; x1 < board.getWidth(); x1++) {

			// check to see if the column is empty...if it is, we can skip it
			if (pieces[x1][0] == null)
				continue;

			// Set up variables to loop over the spaces
			numOfConsecutivePieces = 1;
			potentialConsecutivePieces = 0;
			sign = pieces[x1][0].isMine();
			previousEmpty = false;

			for (int y1 = 1; y1 < board.getHeight(); y1++) {
				if (pieces[x1][y1] == null) { // empty space
					if (!previousEmpty) {
						emptyX = x1;
						emptyY = y1;
					}
					potentialConsecutivePieces++;
					previousEmpty = true;
				} else {
					if (sign != pieces[x1][y1].isMine()) // we've broken the
															// streak
					{
						if (numOfConsecutivePieces + potentialConsecutivePieces >= board
								.getNumToWin()) {
							// this is bad!! Store the threat in the
							// opposingThreats object
							opposingThreats.add(new Opponent(emptyX, emptyY,
									numOfConsecutivePieces,
									potentialConsecutivePieces, sign)); // I can
																		// look
																		// at
							// even/odd
							// later
						}
						numOfConsecutivePieces = 1;
						sign = pieces[x1][y1].isMine();
						if (!previousEmpty) // Decide to count empty spaces or
											// not
							potentialConsecutivePieces = 0;
					} else {
						numOfConsecutivePieces++;
					}
					previousEmpty = false;
				}
			}

			if (previousEmpty || numOfConsecutivePieces == 1) {
				if (numOfConsecutivePieces + potentialConsecutivePieces >= board
						.getNumToWin()) {
					// this is bad!! Store the threat in the opposingThreats
					// object
					opposingThreats.add(new Opponent(emptyX, emptyY,
							numOfConsecutivePieces, potentialConsecutivePieces,
							sign));
				}
			}
		}

		// analyze threat data
		Opponent opposition;
		ArrayList<Opponent> garbage = new ArrayList<Opponent>();
		boolean potentialWin = false;
		for (int i = 0; i < opposingThreats.size(); i++) {
			opposition = opposingThreats.get(i);
			if ((!opposition.isSign())
					&& (opposition.getStreak() == board.getNumToWin() - 1)) {
				// opponent has n-1 in a row!
				if (opposition.getY() > 0) { // can the opponent play there and
												// win?
					if (pieces[opposition.getX()][opposition.getY() - 1] != null)
						return Integer.MIN_VALUE; // lose condition!
				} else {
					return Integer.MIN_VALUE;// lose condition!
				}
			} else if ((opposition.isSign())
					&& (opposition.getStreak() == board.getNumToWin())) {
				if (opposition.getY() > 0) {
					if (pieces[opposition.getX()][opposition.getY() - 1] != null)
						potentialWin = true; // possible win, as long as we
												// don't notice a lose
				}
			}
		}
		if (potentialWin)
			return Integer.MAX_VALUE;

		// remove useless opposing team's movements from the arrayList
		Collections.sort(opposingThreats, Opponent.getComparator());
		ArrayList<Opponent> newThreats = opposingThreats;
		for (int i = (opposingThreats.size() - 1); i > 0; i--) { // if a threat
																	// is
			opposition = opposingThreats.get(i);
			Opponent t2 = opposingThreats.get(i - 1);
			if (opposition.getX() == t2.getX()) {
				if (opposition.getY() >= t2.getY()) {
					if (opposition.isSign() != t2.isSign()) {
						newThreats.remove(i);
						garbage.add(opposition);
					}
				}
			}
		}
		opposingThreats = newThreats;

		// whew! finally, calculate the total number of threats
		int val = 0;
		for (int i = 0; i < opposingThreats.size(); i++) {
			opposition = opposingThreats.get(i);

			//
			val = opposition.getStreak() * 100;
			val += opposition.getPotentialStreak() * 10;
			val += opposition.getY();

			val *= (opposition.getThreat() == board.getPlayerNum()) ? 1.5 : 1;
			// upweight this value is this is their turn

			if (!opposition.isSign()) { // opponent threat
				val *= -1;
				val--; // play defensively
			}

			total += val;
		}

		return total;
	}

}

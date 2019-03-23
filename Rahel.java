package rahel;

import java.util.*;

public class Rahel {
	public static int flag = 1;
	String[] positions;
	List<String> x;
	boolean[][] board; //declaration for data structure for chess board
	
	public Rahel() {
		positions = new String[65];//declaration for 8*8 positions on the board
	}

	public static void main(String[] args) {
		Rahel r = new Rahel();
		r.board = new boolean[8][8]; //2D array declaration which is used as the primary data structure 
		int k = 0, n = 0;
		String str = "";
		r.x = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		System.out.println("All rows are named using numbers [1-8]");
		System.out.println("All columns are named using alphabets [A-H]");
		System.out.println("The positions are named as:");
		char[] B = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
//prints the positions
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				r.positions[k] = (String.valueOf(i + 1) + B[j]);
				System.out.print(r.positions[k] + " ");
				k++;
//positions.append(-1)
				if (i == 0 | i == 1 | i == 6 | i == 7) {
					r.board[i][j] = true;
				} else {
					r.board[i][j] = false;
				}
			}
			System.out.println();
		}
		while (flag == 1) {
			System.out.println("1. King\n2. Knight\n3. Pawn\n4. Bishop\n5. Rook\n6. Queen\n7. Exit\nEnter a value:"); // menu
			n = sc.nextInt();
			if (n == 7) {
				break;
			}
			System.out.println("Enter a position:");
			String pos;
			pos = sc.next();
			int[] b_pos = new int[2];
			b_pos = r.findposition(pos);
//b_pos=Arrays.copyOf(r.findposition(pos), r.findposition(pos).length);
			if (b_pos[0] == -1) {
				System.out.println("Wrong input");
				continue;
			}
			if (r.board[b_pos[0]][b_pos[1]]) {
				System.out.println("Its taken, Please continue.");
				continue;
			}
//below if statements if true; calls the respective methods
			if (n == 1) {
				r.x = r.position_king(b_pos);
				str = "King";
			} else {
				if (n == 6) {
					r.x = r.position_queen(b_pos);
					str = "Queen";
				} else {
					if (n == 5) {
						r.x = r.position_rook(b_pos);
						str = "Rook";
					} else {
						if (n == 2) {
							r.x = r.position_knight(b_pos);
							str = "Knight";
						} else {
							if (n == 4) {
								r.x = r.position_bishop(b_pos);
								str = "Bishop";
							} else {
								if (n == 3) {
									r.x = r.position_pawn(b_pos);
									str = "Pawn";
								} else {
									System.out.println("Wrong Input");
									continue;
								}
							}
						}
					}
				}
			}
			r.x = r.checkpossible(r.x);
			System.out.println("Possible Positions are:" + r.x);
			try {
				System.out.println("Enter the destination position:");
				int dest;
				dest = sc.nextInt();
				int[] destn = new int[2];
				destn = r.findposition(r.x.get(dest));
				if (destn[0] == -1) {
					System.out.println("Wrong input");
					continue;
				}
				r.board[destn[0]][destn[1]] = true;
				System.out.println("***" + str + "placed at" + r.positions[destn[0] * 8 + destn[1]] + "***");
			} catch (Exception e) {
				System.out.println("Wrong input");
				// pass
			}
		}
		System.out.println("Thank you for playing.");
	}
	public int[] findposition(String pos) {
		int i;
		int[] ar = new int[2];
		for (i = 0; i < 65; i++) {
			if (positions[i].equals(pos)) {
				break;
			}

			if (i == 64) {
				ar[0] = -1;
				ar[1] = -1;
				return ar;
			}
		}
		ar[0] = i / 8;
		ar[1] = i % 8;
		return ar;
	}
//below function gives kings moves
	public List position_king(int[] pos) {
		List<String> remaining_pos = new ArrayList<String>();
		int i = pos[0];
		int j = pos[1];
		remaining_pos.add(positions[i * 8 + j]);
		if (i + 1 < 8) {
			remaining_pos.add(positions[(i + 1) * 8 + j]);
		}
		if (i - 1 >= 0) {
			remaining_pos.add(positions[(i - 1) * 8 + j]);
		}
		if (j + 1 < 8) {
			remaining_pos.add(positions[i * 8 + j + 1]);
		}
		if (j - 1 >= 0) {
			remaining_pos.add(positions[(i * 8) + j - 1]);
		}
		if (i + 1 < 8 && j + 1 < 8) {
			remaining_pos.add(positions[((i + 1) * 8) + (j + 1)]);
		}
		if (i + 1 < 8 && j - 1 >= 0) {
			remaining_pos.add(positions[((i + 1) * 8) + (j - 1)]);
		}
		if (i - 1 >= 0 && j - 1 >= 0) {
			remaining_pos.add(positions[((i - 1) * 8) + (j - 1)]);
		}
		if (i - 1 >= 0 && j + 1 < 8) {
			remaining_pos.add(positions[((i - 1) * 8) + (j + 1)]);
		}
		return remaining_pos;
	}
//below function gives queen moves
	public List position_queen(int[] pos) {
		List<String> remaining_pos = new ArrayList<String>();
		// global positions
		// global a
		int i = pos[0];
		int j = pos[1];
		while (i < 8)

			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[(i) * 8 + j]);
		i++;
		i = pos[0];
		j = pos[1];
		while (i - 1 >= 0)

			if (board[i][j])
				break;
		remaining_pos.add(positions[(i - 1) * 8 + j]);
		i--;
		i = pos[0];
		j = pos[1];
		while (j + 1 < 8)
			if (board[i][j])
				break;

		remaining_pos.add(positions[i * 8 + j + 1]);
		j++;
		i = pos[0];
		j = pos[1];
		while (j - 1 >= 0)

			if (board[i][j])
				break;
		remaining_pos.add(positions[(i * 8) + j - 1]);
		j--;
		i = pos[0];
		j = pos[1];
		while (i + 1 < 8 && j + 1 < 8)
			if (board[i][j])
				break;
		remaining_pos.add(positions[((i + 1) * 8) + (j + 1)]);
		i++;
		j++;
		i = pos[0];
		j = pos[1];
		while (i + 1 < 8 && j - 1 >= 0)

			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[((i + 1) * 8) + (j - 1)]);
		i++;
		j--;
		i = pos[0];
		j = pos[1];
		while (i - 1 >= 0 && j - 1 >= 0)
			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[((i - 1) * 8) + (j - 1)]);
		i--;
		j--;
		i = pos[0];
		j = pos[1];
		while (i - 1 >= 0 && j + 1 < 8)

			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[((i - 1) * 8) + (j + 1)]);
		i--;
		j++;
		return remaining_pos;
	}
//below function gives rook moves
	public List position_rook(int[] pos) {
		List<String> remaining_pos = new ArrayList<String>();
		// global positions
		// global a
		int i = pos[0];
		int j = pos[1];
		while (i < 8)
			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[(i) * 8 + j]);
		i++;
		i = pos[0];
		j = pos[1];
		while ((i - 1) >= 0)
			if (board[i][j])
				break;
		remaining_pos.add(positions[(i - 1) * 8 + j]);
		i--;
		i = pos[0];
		j = pos[1];
		while (j + 1 < 8)
			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[i * 8 + j + 1]);
		j++;
		i = pos[0];
		j = pos[1];
		while (j - 1 >= 0)
			if (board[i][j])
				break;
		remaining_pos.add(positions[(i * 8) + j - 1]);
		j--;
		return remaining_pos;
	}

	public List position_bishop(int[] pos) // #function to show bishop moves
	{
		List<String> remaining_pos = new ArrayList<String>();
		// global positions
		// global a
		int i = pos[0];
		int j = pos[1];
		while (i < 8 && j < 8)
			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[((i) * 8) + (j)]);

		i++;
		j++;
		i = pos[0];
		j = pos[1];
		while (i + 1 < 8 && j - 1 >= 0)

			if (board[i][j]) {
				break;
			}
		remaining_pos.add(positions[((i + 1) * 8) + (j - 1)]);
		i++;
		j--;
		i = pos[0];
		j = pos[1];
		while (i - 1 >= 0 && j - 1 >= 0)
			if (board[i][j])
				break;
		remaining_pos.add(positions[((i - 1) * 8) + (j - 1)]);
		i--;
		j--;
		i = pos[0];
		j = pos[1];
		while (i - 1 >= 0 && j + 1 < 8) {
			if (board[i][j]) {
				break;
			}
			remaining_pos.add(positions[((i - 1) * 8) + (j + 1)]);
			i--;
			j++;
		}
		return remaining_pos;
	}

	public List position_pawn(int[] pos)//funtion to show pawn moves
	{
		List<String> remaining_pos = new ArrayList<String>();
		// global positions
		int i = pos[0];
		int j = pos[1];
		remaining_pos.add(positions[i * 8 + j]);
		if (i + 1 < 8)
			remaining_pos.add(positions[(i + 1) * 8 + j]);
		if (i + 1 < 8 && j + 1 < 8) {
			remaining_pos.add(positions[((i + 1) * 8) + (j + 1)]);
		}
		if (i + 1 < 8 && j - 1 >= 0) {
			remaining_pos.add(positions[((i + 1) * 8) + (j - 1)]);
		}
		return remaining_pos;
	}

	public List position_knight(int[] pos)//function to show knight moves
	{
		List<String> remaining_pos = new ArrayList<String>();
		// global positions
		int i = pos[0];
		int j = pos[1];
		remaining_pos.add(positions[i * 8 + j]);
		if (i + 2 < 8 && j + 1 < 8) {
			remaining_pos.add(positions[(i + 2) * 8 + j + 1]);
		}
		if (i + 2 < 8 && j - 1 >= 0) {
			remaining_pos.add(positions[(i + 2) * 8 + j - 1]);
		}
		if (i - 2 >= 0 && j + 1 < 8) {
			remaining_pos.add(positions[(i - 2) * 8 + j + 1]);
		}
		if (i - 2 >= 0 && j - 1 >= 0) {
			remaining_pos.add(positions[(i - 2) * 8 + j - 1]);
		}
		if (j + 2 < 8 && i + 1 < 8) {
			remaining_pos.add(positions[(i + 1) * 8 + j + 2]);
		}
		if (j + 2 < 8 && i - 1 >= 0) {
			remaining_pos.add(positions[(i - 1) * 8 + j + 2]);
		}
		if (j - 2 >= 0 && i + 1 < 8) {
			remaining_pos.add(positions[(i + 1) * 8 + j - 2]);
		}
		if (j - 2 >= 0 && i - 1 >= 0) {
			remaining_pos.add(positions[(i - 1) * 8 + j - 2]);
		}
		return remaining_pos;
	}
	//checks for possible moves
	public List checkpossible(List pos)
	{
		List<String> arr = new ArrayList<String>();
		for (int i = 0; i < pos.size(); i++) {
			int[] x = new int[2];
			x = findposition(pos.get(i).toString());
			if (board[x[0]][x[1]]) {
				continue;
			}
			arr.add(pos.get(i).toString());
		}
		return arr;

	}

}

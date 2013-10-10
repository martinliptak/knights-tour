import java.lang.*;
import java.util.*;

public class State
{
	private int size, x, y, number;
	private int[] board;

	public State(int size, int x, int y, int number, int[] board)
	{
		this.size = size;
		this.x = x;
		this.y = y;
		this.number = number;
		
		// the first state creates a new chessboard
		// ancestors copy the chessboard 
		if (board != null)
			this.board = board;
		else
			this.board = new int[size * size]; 
			
		// add new position
		this.board[x * size + y] = number;
	}
	
	public boolean isFinal()
	{
		return number == size * size;
	}
	
	/**
	 * Add ancestors to the stack
	 */
	public void pushActions(Deque<State> states) 
	{
		// for all possible knight's moves
		pushNewPositionIfValid(states, this.x - 2, this.y + 1);
		pushNewPositionIfValid(states, this.x - 2, this.y - 1);
		pushNewPositionIfValid(states, this.x + 2, this.y - 1);
		pushNewPositionIfValid(states, this.x + 2, this.y + 1);
		pushNewPositionIfValid(states, this.x + 1, this.y + 2);
		pushNewPositionIfValid(states, this.x + 1, this.y - 2);
		pushNewPositionIfValid(states, this.x - 1, this.y + 2);
		pushNewPositionIfValid(states, this.x - 1, this.y - 2);
	}
	
	/**
	 * Used to print the final state.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder(127);
		
		for (int j = size - 1; j >= 0; j--)
		{
			for (int i = 0; i < size; i++)
				sb.append(String.format("%2d ", board[i * size + j]));
			sb.append("\n");
		}
			
		return sb.toString();
	}
	
	/**
	 * Adds a new state to the stack.
	 */
	private void pushNewPositionIfValid(Deque<State> states, int x, int y)
	{
		if (x >= 0 && x < size && y >= 0 && y < size) // is the position on the board?
			if (board[x * size + y] == 0) // did the knight visit this position already?
				states.addFirst(new State(size, x, y, number + 1, board.clone())); // adding state
	}
}


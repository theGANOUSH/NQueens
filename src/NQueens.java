import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NQueens extends java.applet.Applet implements ActionListener 
{
	static final int NUM_ROW = 8, NUM_COLS = 8;
	static final int SQUARE_LENGHT = 50;
	static final int board[][] = new int [NUM_COLS][NUM_ROW];
	
	Image image;
	
	static Button solve =  new Button("Solve It");
	
	public void init()
	{
		setSize(500, 500);
		loadImage();
		
		this.add(solve);
		solve.addActionListener(this);
		
	}
	
	public void paint(Graphics g)
	{
	
		for(int i = 0; i < NUM_COLS; i++)
		{
			for(int j = 0; j < NUM_ROW; j++)
			{
				if(board[i][j] == 1)
				{
					g.drawImage(image, SQUARE_LENGHT + i * SQUARE_LENGHT + 5, SQUARE_LENGHT + SQUARE_LENGHT *j + 5, this);
				}
				g.drawRect(SQUARE_LENGHT + i * SQUARE_LENGHT, SQUARE_LENGHT + SQUARE_LENGHT *j, SQUARE_LENGHT, SQUARE_LENGHT);
			}
			
		}
		
	}
	
	public int checkDiagonal(int col, int row)
	{
		if(col == 0 || row == 0)
		{
			return board[col][row];
		}
		else if( col == NUM_COLS || row == NUM_ROW)
		{
			return 0;
		}
		else
		{
			return checkDiagonal(col -1, row -1) + checkDiagonal(col + 1, row +1) + board[col][row];
		}
		
	}
	
	public boolean checkHorizontal(int col, int row)
	{
		int total = 0;
		
		for(int i = 0; i < NUM_ROW; i++)
		{
			//System.err.println(i + ", " + col + ": " + board[i][col]);
			
			if(board[i][row] == 1)
			{
				total++;
			}
		}
		if(total >= 1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void loadImage()
	{
		image = getImage(getCodeBase(), "queen.png");
	}
	
	public void SolveIt()
	{
		System.err.println("Solve It Pushed");
		
		for(int col = 0; col < NUM_COLS; col++ )
		{
			for(int row = 0; row < NUM_ROW; row++)
			{
				System.err.println(checkHorizontal(col, row));
				if(checkDiagonal(row, col) <= 1)
				{
						board[col][row] = 1;
					
				}
				
				Graphics g = this.getGraphics();
				paint(g);
				
			}
			
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		SolveIt();
		
	}
	
	public static void main(String args[])
	{
		Frame f = new Frame("N-Queens");
		f.setTitle("N-Queens");
		
		NQueens myApplet = new NQueens();
	
		f.add(myApplet);
		myApplet.init();
		f.setVisible(true);
		myApplet.start();
		
	}
	
	
	

}

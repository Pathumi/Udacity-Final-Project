import java.util.Random;

public class MyAgent extends Agent
{
    Random r;

    /**
     * Constructs a new agent, giving it the game and telling it whether it is Red or Yellow.
     * 
     * @param game The game the agent will be playing.
     * @param iAmRed True if the agent is Red, False if the agent is Yellow.
     */
    public MyAgent(Connect4Game game, boolean iAmRed)
    {
        super(game, iAmRed);
        r = new Random();
    }

    /**
     * The move method is run every time it is this agent's turn in the game. You may assume that
     * when move() is called, the game has at least one open slot for a token, and the game has not
     * already been won.
     * 
     * By the end of the move method, the agent should have placed one token into the game at some
     * point.
     * 
     * After the move() method is called, the game engine will check to make sure the move was
     * valid. A move might be invalid if:
     * - No token was place into the game.
     * - More than one token was placed into the game.
     * - A previous token was removed from the game.
     * - The color of a previous token was changed.
     * - There are empty spaces below where the token was placed.
     * 
     * If an invalid move is made, the game engine will announce it and the game will be ended.
     * 
     */
    public void move()
    {
       /* Connect4Game g1=new Connect4Game(7,6);
        g1.getColumn(7);
        g1.getColumnCount();
        g1.getRowCount();
        Connect4Column g2=new Connect4Column;
        
        g2.getIsFull();
        g2.getRowCount();*/
		//int var1;
 		//boolean var1 = false;
        int var2 = this.iCanWin();
        int var3 = this.theyCanWin();
        int var4;
        if (var2 >= 0) {
            var4 = var2;
        } else if (var3 >= 0) {
            var4 = var3;
        } else {
            var4 = this.randomMove();
        }
		

       
		
		this.moveOnColumn(var4);
    }

    /**
     * Drops a token into a particular column so that it will fall to the bottom of the column.
     * If the column is already full, nothing will change.
     * 
     * @param columnNumber The column into which to drop the token.
     */
    public void moveOnColumn(int columnNumber)
    {
        int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));   // Find the top empty slot in the column
                                                                                                  // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = this.myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (iAmRed) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            }
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }
    }

    /**
     * Returns the index of the top empty slot in a particular column.
     * 
     * @param column The column to check.
     * @return the index of the top empty slot in a particular column; -1 if the column is already full.
     */
    public int getLowestEmptyIndex(Connect4Column column) {
        int lowestEmptySlot = -1;
        for  (int i = 0; i < column.getRowCount(); i++)
        {
            if (!column.getSlot(i).getIsFilled())
            {
                lowestEmptySlot = i;
            }
        }
        return lowestEmptySlot;
    }

    /**
     * Returns a random valid move. If your agent doesn't know what to do, making a random move
     * can allow the game to go on anyway.
     * 
     * @return a random valid move.
     */
    public int randomMove()
    {
        int i = r.nextInt(this.myGame.getColumnCount());
        while (getLowestEmptyIndex(this.myGame.getColumn(i)) == -1)
        {
            i = r.nextInt(myGame.getColumnCount());
        }
        return i;
    }

    /**
     * Returns the column that would allow the agent to win.
     * 
     * You might want your agent to check to see if it has a winning move available to it so that
     * it can go ahead and make that move. Implement this method to return what column would
     * allow the agent to win.
     *
     * @return the column that would allow the agent to win.
     */
	public int iCanWin() 
	{//for horizontal match
		for(int i=0; i<this.myGame.getColumnCount();i++)
		{
			int var3 = getLowestEmptyIndex(this.myGame.getColumn(i));
			if(var3==-1)
			{
				if((this.myGame.getColumn(i).getSlot(var3 + 6).getIsRed()) && (this.myGame.getColumn(i).getSlot(var3 + 5).getIsRed()) && (this.myGame.getColumn(i).getSlot(var3 +4).getIsRed()) && (this.myGame.getColumn(i).getSlot(var3 + 3).getIsRed()))
				{
					return i;
				}
			}
			else if((this.myGame.getRowCount()-2)<=var3 && var3<=(this.myGame.getRowCount()-1)) 
			{
				if((this.myGame.getColumn(i).getSlot(var3 -1).getIsRed()) && (this.myGame.getColumn(i).getSlot(var3-2).getIsRed()) && (this.myGame.getColumn(i).getSlot(var3-3).getIsRed()) && (this.myGame.getColumn(i).getSlot(var3-4).getIsRed()))
				{
					return i;
				}
			}
		}
		for(int j=0;j<this.myGame.getRowCount();j++)
		{
			for(int k=0;k<this.myGame.getColumnCount();k++)
			{
				if((this.myGame.getColumn(k).getSlot(j).getIsRed()) && (this.myGame.getColumn(k+1).getSlot(j).getIsRed()) && (this.myGame.getColumn(k+2).getSlot(j).getIsRed()) && (this.myGame.getColumn(k+3).getSlot(j).getIsRed()))
				{
					return k;
				}
			}
			
		}
		// diagonally
		/*for(int l=0; l<=this.myGame.getColumnCount();l++)
		{
			for(int n=0; n<=this.myGame.getRowCount(); n++)
			{
				if((this.myGame.getColumn(l).getSlot(n).getIsRed()) && (this.myGame.getColumn(l+1).getSlot(n+1).getIsRed()) && (this.myGame.getColumn(l+2).getSlot(n+2).getIsRed()) && (this.myGame.getColumn(l+3).getSlot(n+3).getIsRed()))
				{
					return l+3;
				}
			}
			
		}*/
        return -1;
    }
    /*public int iCanWin()
    {
		
        return 0;
    }*/
	
	 
    /**
     * Returns the column that would allow the opponent to win.
     * 
     * You might want your agent to check to see if the opponent would have any winning moves
     * available so your agent can block them. Implement this method to return what column should
     * be blocked to prevent the opponent from winning.
     *
     * @return the column that would allow the opponent to win.
     */
    public int theyCanWin()
    {
		for(int i=0; i<this.myGame.getColumnCount();i++)
		{
			int var3 = this.getLowestEmptyIndex(this.myGame.getColumn(i));
			if(var3==-1)
			{
				if((!this.myGame.getColumn(i).getSlot(var3 + 6).getIsRed()) && (!this.myGame.getColumn(i).getSlot(var3 + 5).getIsRed()) && (!this.myGame.getColumn(i).getSlot(var3 +4).getIsRed()) && (!this.myGame.getColumn(i).getSlot(var3 + 3).getIsRed()))
				{
					return i;
				}
			}
			else if((this.myGame.getRowCount()-2)<=var3 && var3<=(this.myGame.getRowCount()-1))
			{
				if((!this.myGame.getColumn(i).getSlot(var3 -1).getIsRed()) && (!this.myGame.getColumn(i).getSlot(var3-2).getIsRed()) && (!this.myGame.getColumn(i).getSlot(var3-3).getIsRed()) && (!this.myGame.getColumn(i).getSlot(var3-4).getIsRed()))
				{
					return i;
				}
			}
			
		}
		for(int j=0;j<this.myGame.getRowCount();j++)
		{
			for(int k=0;k<this.myGame.getColumnCount();k++)
			{
				if((!this.myGame.getColumn(k).getSlot(j).getIsRed()) && (!this.myGame.getColumn(k).getSlot(j+1).getIsRed()) && (!this.myGame.getColumn(k).getSlot(j+2).getIsRed()) && (!this.myGame.getColumn(k).getSlot(j+3).getIsRed()))
				{
					return k;
				}
			}
			
		}
		/*for(int l=0; l<=this.myGame.getColumnCount();l++)
		{
			for(int n=0; n<=this.myGame.getRowCount(); n++)
			{
				if(!this.myGame.getColumn(l).getSlot(n).getIsRed()&&!this.myGame.getColumn(l+1).getSlot(n+1).getIsRed()&&!this.myGame.getColumn(l+2).getSlot(n+2).getIsRed()&&!this.myGame.getColumn(l+3).getSlot(n+3).getIsRed())
				{
					return l+3;
				}
			}
			
		}*/
        
        return -1;
    }

    /**
     * Returns the name of this agent.
     *
     * @return the agent's name
     */
    public String getName()
    {
        return "My Agent";
    }
}

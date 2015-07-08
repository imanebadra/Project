import java.util.ArrayList;


public class CurlerTeam
{	
	private int numPlayers = 0;
	private int numSkip = 0;
	private int numSweepers = 0;
	private static int numTeams = 0;
	private int numThrowers = 0;
	private ArrayList<CurlerPlayer> players = new ArrayList<CurlerPlayer>();
	private String teamName = null;
	
	public CurlerTeam()
    {
	    numTeams++;
    }
	
	public CurlerTeam(String name)
	{
		this.teamName = name;
		numTeams++;
	}
	
	public String addPlayer(String fname, String lname, String pos)
	{
		if(numPlayers == 4)
			return "There are already 4 members on this team\nPlayer not added";
		CurlerPosition p;
		if(pos.equalsIgnoreCase("sweeper"))
		{
			p = CurlerPosition.SWEEPER;
			if(numSweepers == 2)
				return "There are already 2 sweepers on this team\nPlayer not added";
			numSweepers++;
		}
		else if(pos.equalsIgnoreCase("thrower"))
		{
			p = CurlerPosition.THROWER;
			if(numThrowers == 1)
				return "There is already a thrower on this team\nPlayer not added";
			numThrowers++;
		}
		else
		{
			p = CurlerPosition.SKIP;
			if(numSkip == 1)
				return "There is already a skip on this team\nPlayer not added";
			numSkip++;
		}
		
		numPlayers++;
		
		players.add(new CurlerPlayer(fname, lname, p));
		return null;
	}
	
	public String printTeam()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(teamName);
		sb.append("\n");
		for(CurlerPlayer p : players)
		{
			sb.append(p.getFirstName() + " " + p.getLastName() + " Position: ");
			switch(p.getPosition())
			{
				case SKIP:
					sb.append("skip");
					break;
				case SWEEPER:
					sb.append("sweeper");
					break;
				case THROWER:
					sb.append("thrower");
					break;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public String toString()
	{
		return printTeam();
	}
	
	public int getNumPlayers()
	{
		return numPlayers;
	}
	public static int getNumTeams()
	{
		return numTeams;
	}
}

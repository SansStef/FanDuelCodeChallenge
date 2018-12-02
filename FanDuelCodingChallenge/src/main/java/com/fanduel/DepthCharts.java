package com.fanduel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.fanduel.exception.UnsupportedPositionException;

public class DepthCharts {

	private static final String delimiter = ",";
	private static final String prefix = "[";
	private static final String suffix = "]";
	
	private Map<String, List<Player>> chart;
	private Sport sport;
	
	public DepthCharts(Sport sport) {
		this.sport = sport;
		chart = new HashMap<String, List<Player>>();
	}

	private static void checkIfPositionIsSupported(final Sport sport, final String position) throws UnsupportedPositionException {
		if(!sport.isSupportedPosition(position)) {
			throw new UnsupportedPositionException(position);
		}
	}
	
	/**
	 * Adds a player to a depth chart for a given position (at a specific spot). If you
	 * are entering two players into the same slot, the last player entered gets priority and bumps
	 * the existing player down a depth spot.
	 * @param player
	 * @param position
	 * @param depth
	 * @throws UnsupportedPositionException 
	 */
	public void addPlayerToDepthChart( final Player player, final String position,
			final int depth ) throws UnsupportedPositionException {
		checkIfPositionIsSupported(sport, position);
		
		List<Player> players;
		if(chart.containsKey(position)) {
			players = chart.get(position);
		}
		else {
			players = new LinkedList<Player>();
			chart.put(position, players);
		}
		
		int calculatedDepth = depth > players.size() ? players.size() : depth ;
		
		players.add(calculatedDepth, player);
	}

	/**
	 * Adds a player to the end of the depth chart for a given position.
	 * @param player
	 * @param position
	 * @throws UnsupportedPositionException 
	 */
	public void addPlayerToDepthChart( final Player player, final String position ) throws UnsupportedPositionException {
		addPlayerToDepthChart(player, position, chart.containsKey(position) ? chart.get(position).size() - 1 : 0);
	}

	/**
	 * Removes a player from the depth chart for a position
	 * @param player
	 * @param position
	 * @throws UnsupportedPositionException 
	 */
	public void removePlayerFromDepthChart( final Player player, final String position ) throws UnsupportedPositionException {
		checkIfPositionIsSupported(sport, position);
		chart.get(position).remove(player);
	}

	/**
	 * Prints out all depth chart positions
	 * 
	 * Outputs:
	 * WR: [2, 1, 3]
	 * KR: [1]
	 */
	public void getFullDepthChart() {
		StringBuilder sb = new StringBuilder();
		for(String position : chart.keySet()) {
			sb.append(position)
			.append(": ")
			.append(prefix)
			.append(getPositionPlayers(position))
			.append(suffix)
			.append("\n");
		}
		System.out.println(sb.toString());
	}

	private String getPositionPlayers(final String position) {
		return chart.get(position).stream().map(p -> String.valueOf(p.getId())).collect(Collectors.joining(delimiter));
	}
	
	/**
	 * For a given player find all players below them on the depth chart.
	 * @param player
	 * @param position
	 * @throws UnsupportedPositionException 
	 */
	public void getPlayersUnderPlayerInDepthChart( final Player player, final String position ) throws UnsupportedPositionException {
		checkIfPositionIsSupported(sport, position);
		
		StringJoiner sj = new StringJoiner(delimiter, prefix, suffix);
		
		boolean under = false;
		for(Player p : chart.get(position)) {
			if(!under) {
				under = p.equals(player);
			}
			else {
				sj.add(String.valueOf(p.getId()));
			}
		}
		System.out.println( sj.toString() );
	}
}

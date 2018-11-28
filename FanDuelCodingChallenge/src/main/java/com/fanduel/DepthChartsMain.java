package com.fanduel;

import com.fanduel.exception.UnsupportedPositionException;

public class DepthChartsMain {

	public static void main( String args[] ) throws UnsupportedPositionException {

		final DepthCharts depthCharts = new DepthCharts(Sport.NFL);

		final Player bob = new Player.Builder()
				.id( 1 )
				.name( "Bob" )
				.sport( Sport.NFL )
				.build();

		final Player alice = new Player.Builder()
				.id( 2 )
				.name( "Alice" )
				.sport( Sport.NFL )
				.build();

		final Player charlie = new Player.Builder()
				.id( 3 )
				.name( "Charlie" )
				.sport( Sport.NFL )
				.build();

		depthCharts.addPlayerToDepthChart(bob, "WR", 0);
		depthCharts.addPlayerToDepthChart(alice, "WR", 0);
		depthCharts.addPlayerToDepthChart(charlie, "WR", 2);
		depthCharts.addPlayerToDepthChart(bob, "KR");

		/*
		 * Outputs:
		 * WR: [2, 1, 3]
		 * KR: [1]
		 */
		depthCharts.getFullDepthChart();

		/*
		 * Outputs:
		 * [1,3]
		 */
		depthCharts.getPlayersUnderPlayerInDepthChart(alice, "WR");



	}
}

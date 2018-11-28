package com.fanduel;

public class Player {
	private int id;
	private String name;
	private Sport sport;

	public Player( final Builder builder ) {
		this.id = builder.id;
		this.name = builder.name;
		this.sport = builder.sport;
	}

	public int getId() {
		return id;
	}

	public void setId( final int id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( final String name ) {
		this.name = name;
	}

	public Sport getSport() {
		return sport;
	}
	
	public void setSport( final Sport sport) {
		this.sport = sport;
	}

	public static class Builder {
		private int id;
		private String name;
		private Sport sport;

		public Builder id( final int id ) {
			this.id = id;
			return this;
		}

		public Builder name( final String name ) {
			this.name = name;
			return this;
		}

		public Builder sport( final Sport sport ) {
			this.sport = sport;
			return this;
		}

		public Player build() {
			return new Player( this );
		}
	}
}

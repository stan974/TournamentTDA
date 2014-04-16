package com.projetTDA.metier;

public class Match {
	
	private Equipe equipe1;
	private Equipe equipe2;
	private Integer score1;
	private Integer score2;
	private int rang;
	
	public Match(Equipe equipe1, Equipe equipe2, Integer score1, Integer score2, int rang) {
		super();
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.score1 = score1;
		this.score2 = score2;
		this.rang = rang;
	}

	public Match() {
		super();
	}

	public Equipe getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}

	public Equipe getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}
	
	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("(Rang : ").append(rang).append(")");
		sb.append(this.equipe1);
		sb.append("\n\t");
		sb.append("Score : ");
		sb.append(this.score1);
		sb.append("\n");
		sb.append(this.equipe2);
		sb.append("\n\t");
		//sb.append("|");
		sb.append(this.score2);

		
		return sb.toString();	
	}
	
}

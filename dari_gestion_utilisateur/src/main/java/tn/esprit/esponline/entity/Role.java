package tn.esprit.esponline.entity;



public enum Role {
	CLIENT, ADMINISTRATEUR, AGENT;

	private boolean block;
	private String DescriptionBlock;
	private int nbre;
	
	
	
	public int getNbre() {
		return nbre;
	}



	public void setNbre(int nbre) {
		this.nbre = nbre;
	}



	public String getDescriptionBlock() {
		return DescriptionBlock;
	}



	public void setDescriptionBlock(String descriptionBlock) {
		DescriptionBlock = descriptionBlock;
	}



	public boolean isBlock() {
		return block;
	}



	public void setBlock(boolean block) {
		this.block = block;
	}
}

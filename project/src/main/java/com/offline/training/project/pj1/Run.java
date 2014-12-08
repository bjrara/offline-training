package com.offline.training.project.pj1;

public class Run {
	private int red;
	private int green;
	private int blue;
	private int runLength;

	public Run(int red, int green, int blue, int runLength) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.runLength = runLength;
	}

	public void setValue(int red, int green, int blue, int runLength) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.runLength = runLength;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getRunLength() {
		return runLength;
	}

	public void setRunLength(int runLength) {
		this.runLength = runLength;
	}

	public void Increment() {
		runLength++;
	}

	public void Decrement() {
		runLength--;
	}
}

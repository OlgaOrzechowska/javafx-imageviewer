package com.starterkit.javafx.model;

import java.io.File;

public class ViewImage {

	private File selectedDirectory;

	private File[] images;

	private int selectedIndex;

	public File getSelectedDirectory() {
		return selectedDirectory;
	}

	public void setSelectedDirectory(File selectedDirectory) {
		this.selectedDirectory = selectedDirectory;
	}

	public File[] getImages() {
		return images;
	}

	public void setImages(File[] files) {
		this.images = files;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

}

package com.starterkit.javafx.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class ViewImage {

	private File selectedDirectory;

	private File[] images;

	private final ListProperty<String> imagesProperty = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));

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

	public final List<String> getImagesList() {
		return imagesProperty.get();
	}

	public final void setImagesList(List<String> value) {
		imagesProperty.setAll(value);
	}

	public ListProperty<String> imagesProperty() {
		return imagesProperty;
	}

}

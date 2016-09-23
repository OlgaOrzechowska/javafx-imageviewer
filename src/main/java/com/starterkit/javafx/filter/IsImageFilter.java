package com.starterkit.javafx.filter;

import java.io.File;
import java.io.FileFilter;

public class IsImageFilter implements FileFilter {

	private final String[] imageFileEnds = new String[] { ".jpg", ".png", ".gif" };

	@Override
	public boolean accept(File file) {
		for (String end : imageFileEnds) {
			if (file.getName().toLowerCase().endsWith(end)) {
				return true;
			}
		}
		return false;
	}
}

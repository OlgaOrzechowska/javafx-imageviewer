package com.starterkit.javafx.dataprovider.impl;

import java.io.File;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;
import com.starterkit.javafx.filter.IsImageFilter;

/**
 * Provides data.
 */
public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	public DataProviderImpl() {
	}

	@Override
	public File[] findImages(File selectedDirectory) {
		LOG.debug("findImages() called");

		File[] images = selectedDirectory.listFiles(new IsImageFilter());

		return images;
	}

}

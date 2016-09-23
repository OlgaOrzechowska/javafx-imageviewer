package com.starterkit.javafx.dataprovider;

import java.io.File;

import com.starterkit.javafx.dataprovider.impl.DataProviderImpl;

/**
 * Provides data.
 *
 */
public interface DataProvider {

	DataProvider INSTANCE = new DataProviderImpl();

	File[] findImages(File selectedDirectory);
}

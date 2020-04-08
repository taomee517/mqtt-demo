/***********************************************************************************
 * 
 * Copyright (c) 2014 Kamil Baczkowicz
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *    
 * The Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * 
 *    Kamil Baczkowicz - initial API and implementation and/or initial documentation
 *    
 */
package org.demo.mqtt.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/** 
 * File-related utilities.
 */
@Slf4j
public class FileUtils
{
	public static List<File> getDirectoriesWithFile(final String directory, final String fileToFind)
	{
		final List<File> files = new ArrayList<File>();
		
		final File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null)
		{
			for (int i = 0; i < listOfFiles.length; i++)
			{
				if (listOfFiles[i].isDirectory())
				{
					files.addAll(getDirectoriesWithFile(listOfFiles[i].getAbsolutePath(), fileToFind));																			
				}				
				else if (listOfFiles[i].getName().matches(fileToFind))
				{
					files.add(listOfFiles[i]);
				}
			}
		}		
		
		return files;
	}

	public static List<File> getFileNamesForDirectory(final String directory, final boolean recursive, final String extension)
	{
		final List<File> files = new ArrayList<File>();
		
		final File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null)
		{
			for (int i = 0; i < listOfFiles.length; i++)
			{
				if (listOfFiles[i].isFile())
				{
					if (extension == null || extension.isEmpty() ||  listOfFiles[i].getName().endsWith(extension))
					{
						files.add(listOfFiles[i]);
					}
				}
				else if (recursive)
				{
					files.addAll(getFileNamesForDirectory(listOfFiles[i].getAbsolutePath(), recursive, extension));
				}
			}
		}
		
		if (files.isEmpty())
		{
			log.error("No files in {}", directory);
		}
		
		return files;
	}
	
	public static List<File> getFileNamesForDirectory(final String directory, final String extension)
	{
		return getFileNamesForDirectory(directory, false, extension);
	}
	
	public static void writeToFile(final File file, final String value)
	{
		try
		{
			final PrintWriter out = new PrintWriter(file);					
			out.write(value);
			out.close();
		}
		catch (FileNotFoundException e)
		{
			log.error("Cannot write to file", e);
		}
	}
	
	public static void writeToFile(final File file, final byte[] value)
	{
		try
		{
			Files.write(Paths.get(file.toURI()), value);
		}
		catch (IOException e)
		{
			log.error("Cannot write to file", e);
		}		
	}
	
	public static InputStream loadFileByName(final String filename) throws IOException
	{
		final File file = new File(filename);
		if (file.isFile())
		{
			log.debug("Trying to read {} from filesystem", filename);
			return new FileInputStream(file);
		}
		else
		{
			log.debug("Trying to read {} from classpath", filename);
			final URL url = FileUtils.class.getResource(filename); 
			if (url != null)
			{
				return url.openStream();
			}
			log.debug("File {} not found on classpath", filename);
			return null;
		}
	}
	
//	public static String loadFileByNameAsString(final String filename) throws IOException
//	{
//		final InputStream is = loadFileByName(filename);
//
//		if (is != null)
//		{
//			return IOUtils.toString(is);
//		}
//
//		return null;
//	}
	
//	public static String loadFileByNameBase64Encoded(final String filename) throws IOException
//	{
//		final String fileContent = loadFileByNameAsString(filename);
//
//		if (fileContent != null)
//		{
//			return ConversionUtils.stringToBase64(fileContent);
//		}
//
//		return null;
//	}
	
	/**
	 * Reads lines from the given file and turns that into a list of lines.
	 * 
	 * @param selectedFile The file to read from
	 * 
	 * @return List of lines
	 * 
	 * @throws Exception Thrown when cannot process the given file
	 */
	public static List<String> readFileAsLines(final File selectedFile) throws Exception
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(selectedFile));
	        String str;
			        
	        final List<String> list = new ArrayList<String>();
	        while((str = in.readLine()) != null)
	        {
	        	list.add(str);	        	
	        }
	        
	        in.close();		        		       
	        
	        return list;
		}
		catch (IOException e)
		{
			throw new Exception("Can't open the file at " + selectedFile.getAbsolutePath(), e);
		}
	}
	
	/**
	 * Reads line count from the given file.
	 * 
	 * @param selectedFile The file to read from
	 * 
	 * @return Number of lines
	 * 
	 * @throws Exception Thrown when cannot process the given file
	 */
	public static long countLines(final File selectedFile) throws Exception
	{
		try
		{
			long count = 0;
			BufferedReader in = new BufferedReader(new FileReader(selectedFile));
			        
	        while((in.readLine()) != null)
	        {       
	        	count++;
	        }
	        
	        in.close();		        		       
	        
	        return count;
		}
		catch (IOException e)
		{
			throw new Exception("Can't open the file at " + selectedFile.getAbsolutePath(), e);
		}
	}
}

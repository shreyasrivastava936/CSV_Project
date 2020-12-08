package com.example.csv;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.hibernate.annotations.Any;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Professors;
import com.example.model.Tutorial;


public class CSVHelper {

	public static String type = "text/csv";
	static String[] Headers = { "Id", "Title", "Description", "Published" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if(!type.equals(file.getContentType()))
			return false;

		return true;
	}

	public static <T> List<T> csvToTutorial(InputStream is, String className)
	{
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<T> table_item_list = new ArrayList<>();

			Iterator<CSVRecord> csvRecords = csvParser.iterator();

			switch(className)
			{
			case "Professors":
			{
				List<Professors> p_list = new ArrayList<>();
				while(csvRecords.hasNext())
				{
					CSVRecord csv = csvRecords.next();
					Professors professor = new Professors(
							Long.parseLong(csv.get("Id")),
							csv.get("Name"),
							csv.get("Subject")
							);
					p_list.add(professor);
					return (List<T>) p_list;
				}
			}
			case "Tutorial":
			{
				List<Tutorial> t_list = new ArrayList<>();
				while(csvRecords.hasNext())
				{
					CSVRecord csv = csvRecords.next();
					Tutorial tutorial = new Tutorial(
							Long.parseLong(csv.get("Id")),
							csv.get("Title"),
							csv.get("Description"),
							Boolean.parseBoolean(csv.get("Published"))
							);

					t_list.add(tutorial);
				}
				return (List<T>) t_list;
			}
			default:
				return null;
			}

		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " +e+ e.getMessage());
		}
	}

}
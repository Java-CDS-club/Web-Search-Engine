package com.javaclasses;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class URLtoText_Crawler {

	public static void main(String[] args) {

		try {

			// Initialing local variable used in this program
			int count = 1;
			int pageCount = 15;
			Document documentlink;
			String WebLink = "https://www.javatpoint.com/java-tutorial";
			String fileName = "Web_Crawled/URLtoText.txt";
			String title = "";

			// Create a text file and put its content in text form
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

			System.out.println("Start Crawling of Website - https://www.javatpoint.com/java-tutorial");

			// Setup Http protocol between program and Website
			Document document = Jsoup.connect(WebLink).get();

			// creating to write text in file
			writer.write(document.select("body").text());
			// close writer file
			writer.close();

			// Printing the name of the file
			System.out.println("Name of the file in which the data is stored -  " + fileName);
			System.out.println();

			// Extract page title from website
			title = document.title();
			System.out.println("Title of the page - " + title);

			// Getting all the pages
			Elements elments = document.select("a[href]");

			for (Element el : elments) {

				// Printing the name of the file
				title = document.title();

				// Getting the href element of the page
				String href = el.attr("href");
				if (href.contains("https://www.")) {
					System.out.println("\nWebsite link : " + href);
				} else {
					System.out.println("\nWebsite link : " + "https://www.javatpoint.com/" + href);
				}

				// Getting the text on that page
				String elementText = el.text();
				if (elementText != null && !elementText.isEmpty()) {
					System.out.println("\nContent - " + el.text());
				}

				// checking the http on that page and href of that page
				if (el.attr("href").startsWith("http")) {
					if (count < pageCount) {
						String h = "href";
						documentlink = Jsoup.connect(el.attr(h)).get();

						// Creating different files
						BufferedWriter wl = new BufferedWriter(
								new FileWriter("Web_Crawled/URLtoText" + count + ".txt"));

						// Writing pages content to text files
						wl.write(documentlink.select("body").text());
						wl.close();

						count++;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

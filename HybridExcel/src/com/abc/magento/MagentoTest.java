package com.abc.magento;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MagentoTest {
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	public static int numofrows;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String data;
	public static String action;
	public static WebDriver driver;

	public static String getCellValues(int rownum, int cellnum) {
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		data = cell.getStringCellValue();
		return data;
	}

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\hybrid.xlsx");
		book = new XSSFWorkbook(fis);
		sheet = book.getSheetAt(0);
		numofrows = sheet.getPhysicalNumberOfRows();
		System.out.println(numofrows);
		for (int i = 1; i < numofrows; i++) {
			action = getCellValues(i, 2);
			System.out.println(action);
			switch (action) 
			{
			case "open":
				driver = new FirefoxDriver();
				break;

			case "navigate":
				driver.navigate().to(getCellValues(i, 4));
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				break;

			case "type":
				driver.findElement(By.xpath(getCellValues(i,3))).sendKeys(getCellValues(i,4));
				break;
				
			case "click":
				driver.findElement(By.xpath(getCellValues(i,3))).click();
				break;

			

			case "close":
				driver.close();
				break;
			}
		}
	}
}

package org.datadrivendemo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataDriven {

    public ArrayList<String> getData(String testCaseName) throws IOException {

        // Strategy to access Excel data with poi-ooxml and poi maven dependency setup
        ArrayList<String> testCaseArr = new ArrayList<>();

        //1. the fis object will have access to the Excel file; extension must be '.xlsx'
        FileInputStream fis = new FileInputStream("/Users/hunadimapulane/DemoDocs/datadrivendemodata.xlsx");

        //2 create object of XSSFWorkbook class, it accepts the FileInputStream argument 'fis'
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //Tell workbook to go to the targeted sheet in Excel file
        //Requirement: Get number of sheets in workbook
        int numberOfSheets = workbook.getNumberOfSheets();

        //iterate through the sheets to get access targeted sheet
        for(int i=0; i < numberOfSheets; i++) {

            //if sheet name at index is the targeted sheet
            if(workbook.getSheetName(i).equalsIgnoreCase("demodata")) {

                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator(); //sheet is a collection of rows
                Row firstRow = rows.next();
                Iterator<Cell> cell = firstRow.cellIterator(); //row is a collection of cells

                int columnIndex = 0;
                int actualColumn = 0;

                while(cell.hasNext()) {
                    Cell cellValue = cell.next();
                    if(cellValue.getStringCellValue().equalsIgnoreCase("testcases")) {
                        System.out.println("Cell value is: " + cellValue);
                        actualColumn = columnIndex;
                    }
                    columnIndex++;
                }

                //once test cases column is identified - we must scan through the column to get to the targeted testcase topic
                System.out.println(actualColumn);

                while(rows.hasNext()) {
                    Row columnRow = rows.next();
                    //this ensures we get cell at the targeted column
                    if(columnRow.getCell(actualColumn).getStringCellValue().equalsIgnoreCase(testCaseName)) {

                        //grab all cell content of the row
                        Iterator<Cell> actualCellColumn = columnRow.cellIterator();
                        while (actualCellColumn.hasNext()) {
                            System.out.println(actualCellColumn.next().getStringCellValue());
                            testCaseArr.add(actualCellColumn.next().getStringCellValue());
                        }
                    }
                }
            }
        }

        return testCaseArr;
    }

    public static void main(String[] args) throws IOException {

    }

}


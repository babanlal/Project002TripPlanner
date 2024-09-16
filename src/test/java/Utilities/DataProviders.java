package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
//DP1
@DataProvider(name="LoginData")
public String [][] getData() throws IOException
{
	String path = ".\\testData\\Project2TripPlanner.xlsx";
	
	ExcelUtility xlutil = new ExcelUtility(path);
	
	int totalrows = xlutil.getRowCount("Sheet1");
	int totalcols = xlutil.getCellCount("Sheet1", 1);
	
	String logindata[][] = new String[totalrows][totalcols];  //two dimension array
	for(int i=1; i<=totalrows;i++)//1 read data row in sequence with all cells
	{
		for(int j=0; j<totalcols;j++)//0 read all cells and return to row(i)
		{
			logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0
			System.out.print(logindata);
		}
	}
	return logindata;

//DataProvider2...

}
}

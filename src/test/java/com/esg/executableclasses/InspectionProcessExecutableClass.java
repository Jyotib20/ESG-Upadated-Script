package com.esg.executableclasses;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.esg.testclasses.InspectionProcessTestClass;
import com.esg.utilities.GetScreenShot;
import com.relevantcodes.extentreports.LogStatus;

public class InspectionProcessExecutableClass extends InspectionProcessTestClass {
	
	@BeforeTest
	public static void Intilize() {
		
		initialization();
		}
		@Test(priority = 1)
		public static void LoginStep() {
			test = Report.startTest("Login Step");
			LoginPage();
			Enrollment();
	}@Test(priority = 2)
		public static void Inspection() {
			test = Report.startTest("Inspection");
			InspectionCGIModule();
			InspectionRefusedModule();
			InspectionCancelModule();
			Close();
	}@Test(priority = 3)
	public static void CorrectionModule() {
			test = Report.startTest("Correction");
			//LoginStep();
			CorrectionCGIModule();
			CorrectionRefusedModule();
			CorrectionCancelModule();
			CorrectionNotCompletedModule();
			Close();
	}@Test(priority = 4)
	public static void ReInspectionModule() {
		test = Report.startTest("ReInspection");
		//LoginStep();
		ReInspectionCGIModule();
		ReInspectionRefusedModule();
		ReInspectionCancelModule();
		Close();
	}@Test(priority = 5)
	public static void CorrectionStepDisabledAllFailModule() {
		test = Report.startTest("Correction Step Disabled AllFail");
		//LoginStep();
		CorrectionStepDisabledAllFail();
		CorrectionStepDisabledPartialFail();
		Close();
		
	}@Test(priority = 6)
	public static void FailCodeNocorrectionRequiredModule() {
		test = Report.startTest("FailCode No correction Required");
		//LoginStep();
		FailCodeNoCorrectionRequired();
		Close();
	}@Test(priority = 7)
	public static void PositiveWorkflows() {
		test = Report.startTest("Positive Workflow");	
	//	LoginStep();
		PositiveCorrectionAndReInspection();
		PositiveInspectionNoCorrectionNeeded();
		Close();
	}
		@AfterMethod
		 public void getResult(ITestResult result) throws IOException
		    {
		        if(result.getStatus() == ITestResult.FAILURE)
		        {
		            String screenShotPath = GetScreenShot.capture(driver);
		            test.log(LogStatus.FAIL, result.getThrowable());
		            test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
		        }
		        Report.endTest(test);
		    }

		@AfterTest
		 public static void Generate() {
			 
			generateReport();
		 }

}

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// TC03 - Search Product Positive (DDT): keyword, expected_result di-binding dari data_search_keyword
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL)
WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Header/input_Search'), GlobalVariable.TIMEOUT)
WebUI.setText(findTestObject('Object Repository/Page_Header/input_Search'), keyword)
WebUI.click(findTestObject('Object Repository/Page_Header/btn_Search'))

// Expected: tabel hasil berisi minimal 1 produk, dan produk expected_result ada di tabel
WebUI.verifyElementVisible(findTestObject('Object Repository/Page_Catalog/lbl_SearchResult'))
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Catalog/lbl_ProductName', [('productName') : expected_result]),
    GlobalVariable.TIMEOUT)
WebUI.comment("Keyword '$keyword' menemukan produk '$expected_result'")

WebUI.closeBrowser()

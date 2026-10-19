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

// TC02 - Login Negative (DDT): username, password, expected_message di-binding dari data_login_negative
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL)
WebUI.maximizeWindow()

WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Header/btn_SignIn'), GlobalVariable.TIMEOUT)
WebUI.click(findTestObject('Object Repository/Page_Header/btn_SignIn'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Login/input_Username'), GlobalVariable.TIMEOUT)
WebUI.setText(findTestObject('Object Repository/Page_Login/input_Username'), username)
WebUI.setText(findTestObject('Object Repository/Page_Login/input_Password'), password)
WebUI.click(findTestObject('Object Repository/Page_Login/btn_Login'))

if (expected_message?.trim()) {
    // Kredensial salah -> JPetStore menampilkan pesan error
    WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Login/lbl_ErrorMessage'), GlobalVariable.TIMEOUT)
    String actualMessage = WebUI.getText(findTestObject('Object Repository/Page_Login/lbl_ErrorMessage')).replaceAll('\\s+', ' ').trim()
    WebUI.comment("Actual error message: $actualMessage")
    WebUI.verifyMatch(actualMessage, expected_message, false)
} else {
    // Field kosong -> validasi form, tidak ada pesan error dan form login tetap tampil
    WebUI.verifyElementVisible(findTestObject('Object Repository/Page_Login/input_Username'))
}

// User tidak boleh berhasil login
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_Header/btn_SignOut'), 3)

WebUI.closeBrowser()

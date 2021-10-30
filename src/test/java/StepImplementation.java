
import com.testinium.base.BaseTest;
import com.testinium.base.BaseFunctions;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.Random;



public class StepImplementation extends BaseTest implements BaseFunctions{
    private static final Logger log = LogManager.getLogger(StepImplementation.class);

    @Step("Uygulamanın açıldığını kontrol et")
    public void checkApp() throws InterruptedException {
        BaseFunctions.checkOpenApp();
    }
    @Step("Aşağı doğru kaydır")
    public void scrollToBottom(){
        BaseFunctions.scrollToBottom();
    }

    @Step("<elemntID> listesini bul(ID).")
    public List<MobileElement> findListByID(String elemntID) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elemntID)));
        return appiumDriver.findElements(By.id(elemntID));
    }

    @Step("<elemntXpath> listesini bul(Xpath).")
    public List<MobileElement> findListByXpath(String elemntXpath) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elemntXpath)));
        return appiumDriver.findElements(By.xpath(elemntXpath));
    }
    @Step("<elemntID> rastgele sec(ID).")
    public void randomSelectID(String elemntID) {
        Random rand = new Random();
        findListByID(elemntID).get(rand.nextInt(findListByID(elemntID).size())).click();
    }

    @Step("<elemntXpath> rastgele sec(Xpath).")
    public void randomSelectXpath(String elemntXpath) {
        Random rand = new Random();
        findListByXpath(elemntXpath).get(rand.nextInt(findListByXpath(elemntXpath).size() + 1)).click();
    }

    @Step("<elmntID> elemanı tıklanabilir olunca tıkla(ID)")
    public void clickByID(String elmntID){
        BaseFunctions.clickByID(elmntID);
    }
    @Step("<elemntXpath> elamanı tıklanabilir olunca tıkla(xpath)")
        public void clickbyXpath(String elemntXpath){
            BaseFunctions.clickByXpath(elemntXpath);
        }

    @Step("<seconds> saniye bekle")
    public void waitSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }

    @Step("<scrollTimes> kere Aşağı doğru kaydır")
    public static void scrollToBottom(int scrollTimes) throws InterruptedException {
        for (int i=1;i<=scrollTimes;i++){
            BaseFunctions.scrollToBottom();
            Thread.sleep(1000);
        }
    }


    @Step("<elemntID> tiklanabilir urun bedenini tikla.(ID)")
    public void findProductSizeByID(String elemntID) {
        List<MobileElement> sizeList = findListByID(elemntID);
        for (MobileElement element : sizeList) {
            if (element.isEnabled())
                element.click();
            break;
        }
    }
    @Step("<elemntXpath> tiklanabilir urun bedenini tikla.(Xpath)")
    public void findProductSizeByXpath(String elemntXpath) {
        List<MobileElement> sizeList = findListByXpath(elemntXpath);
        for (MobileElement element : sizeList) {
            if (element.isEnabled())
                element.click();
            break;
        }
    }


    @Step("<elemntID> elementine <text> yaz.(ID)")
    public void sendKeysByID(String elemntID, String text) {
        BaseFunctions.sendKeysID(elemntID,text);
    }

    @Step("<elemntXpath> elementine <text> yaz.(Xpath)")
    public void sendKeysByXpath(String elemntXpath, String text) {
        BaseFunctions.sendKeysXpath(elemntXpath,text);
    }

    @Step("<elemntID> elementini bul ve <text> metni ile sayfa kontrolunu yap(ID)")
    public void ControlByID(String elemntID, String text) { Assert.assertEquals(text, appiumDriver.findElementById(elemntID).getText()); }

    @Step("<elemntXpath> elementini bul ve <text> metni ile sayfa kontrolunu yap(XPATH)")
    public void ControlByXpath(String elemntXpath, String text) { Assert.assertEquals(text, appiumDriver.findElementByXPath(elemntXpath).getText());    }

    @Step("<elemnt_id> elementinin seçilebilir durumunu kontrol et(ID)")
    public static void checkSelectedByID(String elemnt_id){
        BaseFunctions.isSelectedCheckById(elemnt_id);
    }
    @Step("<elemntxpath> elementinin seçilebilir durumunu kontrol et(Xpath)")
    public static void checkSelectedByXpath(String elemntxpath){
        BaseFunctions.isSelectedCheckByXpath(elemntxpath);
    }


}

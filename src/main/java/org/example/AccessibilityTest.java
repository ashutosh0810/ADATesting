package org.example;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AccessibilityTest extends BaseClass {
    @Test
    public void testAccessibility() {
        driver.get("https://magento.softwaretestingboard.com/");
        // path of the axe.min.js file 
        File axeCoreFile = new File("axe.min.js");
        URL scriptUrl = null;
        try {
            scriptUrl = axeCoreFile.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Analyze the page using axe-core
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");
        if (violations.length() == 0) {
            // No violations, test passes!
            test.pass("No accessibility violations found");
        } else {
            // violations found, test fails.
            AXE.writeResults("AccessibilityTest", responseJSON);
            test.fail(AXE.report(violations));
        }
    }
}

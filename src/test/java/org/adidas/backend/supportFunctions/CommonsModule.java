package org.adidas.backend.supportFunctions;

import net.serenitybdd.core.Serenity;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CommonsModule {
    public static JSONObject getJSONfromFile(String filePath) {
        try {
            return new JSONObject(FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8));
        } catch (IOException exception) {
            Assert.fail("File: " + filePath + " was not found.");
            return null;
        }
    }

    public static <T> T getSessionVariable(String key) {
        return Serenity.sessionVariableCalled(key);
    }

    public static <T> void setSessionVariable(String key, T value) {
        Serenity.setSessionVariable(key).to(value);
    }
}

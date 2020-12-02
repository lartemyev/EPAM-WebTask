package lib;

import lib.exceptions.UnknownBrowserException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum TestBrowser {
    FIREFOX,
    CHROME;

    private static Map<String, TestBrowser> browserMap = new HashMap<String, TestBrowser>();

    static {
        browserMap.put("firefox", TestBrowser.FIREFOX);
        browserMap.put("chrome", TestBrowser.CHROME);
    }

    public static TestBrowser Browser(String name) {
        TestBrowser browserType = browserMap.get(name.toLowerCase().trim());
        if (browserType == null) {
            throw new UnknownBrowserException("Unknown browser [" + name + "]. Use one of following: "
                    + StringUtils.join(browserMap.keySet().toArray(), ", "));
        }
        return browserType;
    }
}

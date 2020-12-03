package lib;

import lib.exceptions.UnknownBrowserException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum Browser {
    FIREFOX,
    CHROME;

    private static final Map<String, Browser> browserMap = new HashMap<>();

    // using static initializer to avoid duplicate load call's
    static {
        String BrowserName_CHROME = "chrome";
        String browserName_FIREFOX = "firefox";
        browserMap.put(browserName_FIREFOX, FIREFOX);
        browserMap.put(BrowserName_CHROME, CHROME);
    }

    public static Browser init (String name) {
        Browser browserType = browserMap.get(name.toLowerCase().trim());
        if (browserType == null) {
            throw new UnknownBrowserException("Unknown browser is used: [" + name + "]. Use one of following: "
                    + StringUtils.join(browserMap.keySet().toArray(), ", "));
        }
        return browserType;
    }
}

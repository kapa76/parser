package org.parser;

import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.impl.cookie.BasicExpiresHandler;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.util.TextUtils;

/**
 * Created by G73 on 13.09.2015.
 */
public class LenientCookieSpec extends BrowserCompatSpec {

    private final String[] DATE_PATTERNS = {"dd mm yyyy","d mm yyyy","dd/mm-yyyy"};

    public LenientCookieSpec() {
        super();
        registerAttribHandler(ClientCookie.EXPIRES_ATTR, new BasicExpiresHandler(DATE_PATTERNS) {
            @Override
            public void parse(SetCookie cookie, String value) throws MalformedCookieException {
                if (TextUtils.isEmpty(value)) {
                    // You should set whatever you want in cookie
                    cookie.setExpiryDate(null);
                } else {
                    super.parse(cookie, value);
                }
            }
        });
    }
}
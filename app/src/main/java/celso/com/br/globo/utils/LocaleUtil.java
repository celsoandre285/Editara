package celso.com.br.globo.utils;

import java.util.Locale;

public class LocaleUtil {
    private static final Locale locale = new Locale("pt", "BR");
    private static final Locale localeEN = new Locale("en", "US");

    public static Locale getDefaultLocale() {
        return locale;
    }

    public static Locale getLocaleEN() {
        return localeEN;
    }
}

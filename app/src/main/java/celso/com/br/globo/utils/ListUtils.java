package celso.com.br.globo.utils;

import java.util.List;

public class ListUtils {

    public static <T extends Object> boolean listEmptyOrNull(List<T> values) {
        return values == null || values.isEmpty();
    }

}


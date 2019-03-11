package celso.com.br.globo.utils;

import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

public class ShimmerUtil {

    public static void start(ShimmerFrameLayout layout) {
        layout.startShimmer();
        layout.setVisibility(View.VISIBLE);
    }

    public static void stop(ShimmerFrameLayout layout) {
        layout.stopShimmer();
        layout.setVisibility(View.GONE);
    }
}

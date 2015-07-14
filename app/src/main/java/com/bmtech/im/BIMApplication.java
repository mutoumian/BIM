package com.bmtech.im;

import android.widget.Toast;

import net.java.sip.communicator.service.protocol.AccountManager;
import net.java.sip.communicator.util.ServiceUtils;

import org.jitsi.android.JitsiApplication;
import org.jitsi.android.gui.AndroidGUIActivator;
import org.jitsi.android.gui.account.AccountLoginActivity;
import org.osgi.framework.BundleContext;

/**
 *
 *
 * Created by lycoris on 2015/5/26.
 */
public class BIMApplication extends JitsiApplication {

    /**
     * Returns home <tt>Activity</tt> class.
     *
     * @return Returns home <tt>Activity</tt> class.
     */
    public static Class<?> getHomeScreenActivityClass() {
        BundleContext osgiContext = AndroidGUIActivator.bundleContext;
        if (osgiContext == null) {
            // If OSGI has not started show splash screen as home
            return android.app.LauncherActivity.class;
        }

        AccountManager accountManager = ServiceUtils.getService(osgiContext, AccountManager.class);

        // If account manager is null it means that OSGI has not started yet
        if (accountManager == null) return android.app.LauncherActivity.class;

        final int accountCount = accountManager.getStoredAccounts().size();

        if (accountCount == 0) {
            // Start new account Activity
            return AccountLoginActivity.class;
        }
        else {
            // Start main view
            return MainActivity.class;
        }
    }

    public static void test(){
        System.out.print("123");
    }

}

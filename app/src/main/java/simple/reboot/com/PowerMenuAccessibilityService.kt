package simple.reboot.com

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent

class PowerMenuAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Not needed for this specific implementation
    }

    override fun onInterrupt() {
        // Not needed
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        val action = event.action

        if (keyCode == KeyEvent.KEYCODE_POWER) {
            if (action == KeyEvent.ACTION_DOWN) {
                // We detect the down press. If it's held, we can trigger our menu.
                // However, directly intercepting long press power is restricted on many Android versions.
                // A common workaround is to detect if it's held for a certain duration.
                
                // For this implementation, we'll try to start MainActivity when a long press is simulated
                // or intercepted if the system allows it via filter.
                
                // Note: Intercepting the REAL power menu usually requires being a system app or 
                // using specialized flags that might not work on all OEMs.
            }
        }
        return super.onKeyEvent(event)
    }

    private fun showRebootMenu() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }
        startActivity(intent)
    }
}
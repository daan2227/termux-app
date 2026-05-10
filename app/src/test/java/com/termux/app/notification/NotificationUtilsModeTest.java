package com.termux.app.notification;

import com.termux.shared.notification.NotificationUtils;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NotificationUtilsModeTest {

    @Test
    public void modeConstantsAreDistinct() {
        int[] modes = {
            NotificationUtils.NOTIFICATION_MODE_NONE,
            NotificationUtils.NOTIFICATION_MODE_SILENT,
            NotificationUtils.NOTIFICATION_MODE_SOUND,
            NotificationUtils.NOTIFICATION_MODE_VIBRATE,
            NotificationUtils.NOTIFICATION_MODE_LIGHTS,
            NotificationUtils.NOTIFICATION_MODE_SOUND_AND_VIBRATE,
            NotificationUtils.NOTIFICATION_MODE_SOUND_AND_LIGHTS,
            NotificationUtils.NOTIFICATION_MODE_VIBRATE_AND_LIGHTS,
            NotificationUtils.NOTIFICATION_MODE_ALL
        };
        Set<Integer> seen = new HashSet<>();
        for (int mode : modes) {
            assertTrue("Duplicate NOTIFICATION_MODE constant: " + mode, seen.add(mode));
        }
        assertEquals(9, seen.size());
    }

    @Test
    public void noneIsZero() {
        assertEquals(0, NotificationUtils.NOTIFICATION_MODE_NONE);
    }

    @Test
    public void allModesAreNonNegative() {
        int[] modes = {
            NotificationUtils.NOTIFICATION_MODE_NONE,
            NotificationUtils.NOTIFICATION_MODE_SILENT,
            NotificationUtils.NOTIFICATION_MODE_SOUND,
            NotificationUtils.NOTIFICATION_MODE_VIBRATE,
            NotificationUtils.NOTIFICATION_MODE_LIGHTS,
            NotificationUtils.NOTIFICATION_MODE_SOUND_AND_VIBRATE,
            NotificationUtils.NOTIFICATION_MODE_SOUND_AND_LIGHTS,
            NotificationUtils.NOTIFICATION_MODE_VIBRATE_AND_LIGHTS,
            NotificationUtils.NOTIFICATION_MODE_ALL
        };
        for (int mode : modes) {
            assertTrue("Mode constant must be >= 0: " + mode, mode >= 0);
        }
    }
}

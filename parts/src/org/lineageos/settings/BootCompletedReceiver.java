/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2020 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
<<<<<<< HEAD
import android.util.Log;
=======
import android.os.IBinder;
import android.util.Log;
import android.view.Display.HdrCapabilities;
import android.view.SurfaceControl;
>>>>>>> abcff4f (marble: parts: Implement profile-specific Dolby settings)

import org.lineageos.settings.doze.DozeUtils;
import org.lineageos.settings.dirac.DiracUtils;
<<<<<<< HEAD
=======
import org.lineageos.settings.dolby.DolbyUtils;
import org.lineageos.settings.doze.PocketService;
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
import org.lineageos.settings.thermal.ThermalUtils;
import org.lineageos.settings.haptic.HapticUtils;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "XiaomiParts";

    private static final String TAG = "XiaomiParts-BCR";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (!intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            return;
        }
        if (DEBUG)
            Log.d(TAG, "Received boot completed intent");
	// Doze
        DozeUtils.onBootCompleted(context);

        Log.i(TAG, "Boot completed");

        // Dirac
<<<<<<< HEAD
<<<<<<< HEAD
        // DiracUtils.onBootCompleted(context);

        // Dolby Atmos
        DolbyUtils.getInstance(context);
=======
        DiracUtils.onBootCompleted(context);
>>>>>>> parent of 532e08c (sm6150-common: parts: Introduce Dolby Atmos)
=======
        // DiracUtils.onBootCompleted(context);

        // Dolby Atmos
<<<<<<< HEAD
        DolbyUtils.getInstance(context).onBootCompleted();
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
=======
        DolbyUtils.getInstance(context);
>>>>>>> abcff4f (marble: parts: Implement profile-specific Dolby settings)

        // Thermal Profiles
        ThermalUtils.startService(context);

        // Haptic
        HapticUtils.restoreLevel(context);
    }
}

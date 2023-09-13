/*
 * Copyright (C) 2018,2020 The LineageOS Project
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

package org.lineageos.settings.dolby;

<<<<<<< HEAD
import static org.lineageos.settings.dolby.DolbyAtmos.DsParam;

import android.content.Context;
import android.util.Log;

import org.lineageos.settings.R;
=======
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.SystemClock;
import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)

import org.lineageos.settings.R;

import java.util.Arrays;
import java.util.List;

public final class DolbyUtils {

    private static final String TAG = "DolbyUtils";
<<<<<<< HEAD
=======
    private static final String DEFAULT_PRESET = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
    private static final int EFFECT_PRIORITY = 100;

    private static DolbyUtils mInstance;
    private DolbyAtmos mDolbyAtmos;
<<<<<<< HEAD
    private Context mContext;

    public DolbyUtils(Context context) {
        mContext = context;
        mDolbyAtmos = new DolbyAtmos(EFFECT_PRIORITY, 0);
        mDolbyAtmos.setEnabled(mDolbyAtmos.getDsOn());
=======
    private MediaSessionManager mMediaSessionManager;
    private Context mContext;
    private Handler mHandler = new Handler();

    private DolbyUtils(Context context) {
        mContext = context;
        mDolbyAtmos = new DolbyAtmos(EFFECT_PRIORITY, 0);
        mMediaSessionManager = context.getSystemService(MediaSessionManager.class);
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
    }

    public static synchronized DolbyUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DolbyUtils(context);
        }
        return mInstance;
    }

<<<<<<< HEAD
=======
    public void onBootCompleted() {
        Log.i(TAG, "onBootCompleted");
        mDolbyAtmos.setEnabled(mDolbyAtmos.getDsOn());
        mDolbyAtmos.setVolumeLevelerEnabled(false);

        // // Make sure to apply our configuration
        // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        // boolean dsOn = prefs.getBoolean(DolbySettingsFragment.PREF_ENABLE, true);
        // if (!dsOn) {
        //     // Skip if dolby is off, maybe controlled by other dax app
        //     Log.i(TAG, "dolby is off, skip configuration");
        //     return;
        // }
        // int profile = Integer.parseInt(prefs.getString(
        //         DolbySettingsFragment.PREF_PROFILE, "0" /* dynamic */));
        // String preset = prefs.getString(DolbySettingsFragment.PREF_PRESET, DEFAULT_PRESET);
        // setDsOn(dsOn);
        // setProfile(profile);
        // setPreset(preset);
    }

    private void triggerPlayPause(MediaController controller) {
        long when = SystemClock.uptimeMillis();
        final KeyEvent evDownPause = new KeyEvent(when, when, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PAUSE, 0);
        final KeyEvent evUpPause = KeyEvent.changeAction(evDownPause, KeyEvent.ACTION_UP);
        final KeyEvent evDownPlay = new KeyEvent(when, when, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY, 0);
        final KeyEvent evUpPlay = KeyEvent.changeAction(evDownPlay, KeyEvent.ACTION_UP);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evDownPause);
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evUpPause);
            }
        }, 20);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evDownPlay);
            }
        }, 1000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evUpPlay);
            }
        }, 1020);
    }

    private int getMediaControllerPlaybackState(MediaController controller) {
        if (controller != null) {
            final PlaybackState playbackState = controller.getPlaybackState();
            if (playbackState != null) {
                return playbackState.getState();
            }
        }
        return PlaybackState.STATE_NONE;
    }

    private void refreshPlaybackIfNecessary(){
        if (mMediaSessionManager == null) return;

        final List<MediaController> sessions
                = mMediaSessionManager.getActiveSessionsForUser(
                null, UserHandle.ALL);
        for (MediaController controller : sessions) {
            if (PlaybackState.STATE_PLAYING ==
                    getMediaControllerPlaybackState(controller)) {
                triggerPlayPause(controller);
                break;
            }
        }
    }

>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
    private void checkEffect() {
        if (!mDolbyAtmos.hasControl()) {
            Log.w(TAG, "lost control, recreating effect");
            mDolbyAtmos.release();
            mDolbyAtmos = new DolbyAtmos(EFFECT_PRIORITY, 0);
        }
    }

    public void setDsOn(boolean on) {
        checkEffect();
<<<<<<< HEAD
        Log.d(TAG, "setDsOn: " + on);
        mDolbyAtmos.setDsOn(on);
=======
        Log.i(TAG, "setDsOn: " + on);
        mDolbyAtmos.setDsOn(on);
        if (on) {
            refreshPlaybackIfNecessary();
        }
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
    }

    public boolean getDsOn() {
        boolean on = mDolbyAtmos.getDsOn();
<<<<<<< HEAD
        Log.d(TAG, "getDsOn: " + on);
=======
        Log.i(TAG, "getDsOn: " + on);
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
        return on;
    }

    public void setProfile(int index) {
        checkEffect();
<<<<<<< HEAD
        Log.d(TAG, "setProfile: " + index);
=======
        Log.i(TAG, "setProfile: " + index);
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
        mDolbyAtmos.setProfile(index);
    }

    public int getProfile() {
        int profile = mDolbyAtmos.getProfile();
<<<<<<< HEAD
        Log.d(TAG, "getProfile: " + profile);
        return profile;
    }

    public String getProfileName() {
        String profile = Integer.toString(mDolbyAtmos.getProfile());
        List<String> profiles = Arrays.asList(mContext.getResources().getStringArray(
                R.array.dolby_profile_values));
        int profileIndex = profiles.indexOf(profile);
        Log.d(TAG, "getProfileName: profile=" + profile + " index=" + profileIndex);
        return profileIndex == -1 ? null : mContext.getResources().getStringArray(
                R.array.dolby_profile_entries)[profileIndex];
    }

    public void resetProfileSpecificSettings() {
        checkEffect();
        mDolbyAtmos.resetProfileSpecificSettings();
    }

    public void setPreset(String preset) {
        checkEffect();
        int[] gains = Arrays.stream(preset.split(",")).mapToInt(Integer::parseInt).toArray();
        Log.d(TAG, "setPreset: " + Arrays.toString(gains));
        mDolbyAtmos.setDapParameter(DsParam.GEQ, gains);
    }

    public String getPreset() {
        int[] gains = mDolbyAtmos.getDapParameter(DsParam.GEQ);
        Log.d(TAG, "getPreset: " + Arrays.toString(gains));
        String[] preset = Arrays.stream(gains).mapToObj(String::valueOf).toArray(String[]::new);
        return String.join(",", preset);
    }

    public void setBassEnhancerEnabled(boolean enable) {
        checkEffect();
        Log.d(TAG, "setBassEnhancerEnabled: " + enable);
        mDolbyAtmos.setDapParameterBool(DsParam.BASS_ENHANCER, enable);
    }

    public boolean getBassEnhancerEnabled() {
        boolean enabled = mDolbyAtmos.getDapParameterBool(DsParam.BASS_ENHANCER);
        Log.d(TAG, "getBassEnhancerEnabled: " + enabled);
        return enabled;
    }

    public void setDialogueEnhancerAmount(int amount) {
        checkEffect();
        Log.d(TAG, "setDialogueEnhancerAmount: " + amount);
        mDolbyAtmos.setDapParameterBool(DsParam.DIALOGUE_ENHANCER_ENABLE, amount > 0);
        mDolbyAtmos.setDapParameterInt(DsParam.DIALOGUE_ENHANCER_AMOUNT, amount);
    }

    public int getDialogueEnhancerAmount() {
        boolean enabled = mDolbyAtmos.getDapParameterBool(DsParam.DIALOGUE_ENHANCER_ENABLE);
        int amount = enabled ? mDolbyAtmos.getDapParameterInt(DsParam.DIALOGUE_ENHANCER_AMOUNT) : 0;
        Log.d(TAG, "getDialogueEnhancerAmount: enabled=" + enabled + " amount=" + amount);
        return amount;
    }

    public void setStereoWideningAmount(int amount) {
        checkEffect();
        Log.d(TAG, "setStereoWideningAmount: " + amount);
        mDolbyAtmos.setDapParameterBool(DsParam.HEADPHONE_VIRTUALIZER, amount > 0);
        mDolbyAtmos.setDapParameterInt(DsParam.STEREO_WIDENING, amount);
    }

    public int getStereoWideningAmount() {
        boolean enabled = mDolbyAtmos.getDapParameterBool(DsParam.HEADPHONE_VIRTUALIZER);
        int amount = enabled ? mDolbyAtmos.getDapParameterInt(DsParam.STEREO_WIDENING) : 0;
        Log.d(TAG, "getStereoWideningAmount: enabled=" + enabled + " amount=" + amount);
        return amount;
    }

    public void setVolumeLevelerEnabled(boolean enable) {
        checkEffect();
        Log.d(TAG, "setVolumeLevelerEnabled: " + enable);
        mDolbyAtmos.setDapParameterBool(DsParam.VOLUME_LEVELER, enable);
    }

    public boolean getVolumeLevelerEnabled() {
        boolean enabled = mDolbyAtmos.getDapParameterBool(DsParam.VOLUME_LEVELER);
        Log.d(TAG, "getVolumeLevelerEnabled: " + enabled);
        return enabled;
=======
        Log.i(TAG, "getProfile: " + profile);
        return profile;
    }

    public String getProfileName() {
        String profile = Integer.toString(mDolbyAtmos.getProfile());
        List<String> profiles = Arrays.asList(mContext.getResources().getStringArray(
                R.array.dolby_profile_values));
        int profileIndex = profiles.indexOf(profile);
        Log.i(TAG, "getProfileAsString: profile=" + profile + " index=" + profileIndex);
        return profileIndex == -1 ? null : mContext.getResources().getStringArray(
                R.array.dolby_profile_entries)[profileIndex];
    }

    public void setPreset(String preset) {
        checkEffect();
        int[] gains = Arrays.stream(preset.split(",")).mapToInt(Integer::parseInt).toArray();
        Log.i(TAG, "setPreset: " + Arrays.toString(gains));
        mDolbyAtmos.setGeqBandGains(gains);
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
    }
}

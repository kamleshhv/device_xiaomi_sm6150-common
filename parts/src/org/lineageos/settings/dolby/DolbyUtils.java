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
import android.util.Log;
<<<<<<< HEAD
import android.view.KeyEvent;
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
=======
>>>>>>> dc54f9d (marble: parts: Remove play/pause hack while toggling Dolby)

import org.lineageos.settings.R;

import java.util.Arrays;
import java.util.List;

public final class DolbyUtils {

    private static final String TAG = "DolbyUtils";
<<<<<<< HEAD
=======
    private static final String DEFAULT_PRESET = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
    private static final int EFFECT_PRIORITY = 100;

    private static DolbyUtils mInstance;
    private DolbyAtmos mDolbyAtmos;
<<<<<<< HEAD
<<<<<<< HEAD
    private Context mContext;

    public DolbyUtils(Context context) {
        mContext = context;
        mDolbyAtmos = new DolbyAtmos(EFFECT_PRIORITY, 0);
        mDolbyAtmos.setEnabled(mDolbyAtmos.getDsOn());
=======
    private MediaSessionManager mMediaSessionManager;
=======
>>>>>>> dc54f9d (marble: parts: Remove play/pause hack while toggling Dolby)
    private Context mContext;

    private DolbyUtils(Context context) {
        mContext = context;
        mDolbyAtmos = new DolbyAtmos(EFFECT_PRIORITY, 0);
<<<<<<< HEAD
        mMediaSessionManager = context.getSystemService(MediaSessionManager.class);
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
=======
>>>>>>> dc54f9d (marble: parts: Remove play/pause hack while toggling Dolby)
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
    }

>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
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
<<<<<<< HEAD
<<<<<<< HEAD
        if (on) {
            refreshPlaybackIfNecessary();
        }
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
=======
        refreshPlaybackIfNecessary();
>>>>>>> 92d341b (marble: parts: Always refresh playback if status changed)
=======
>>>>>>> dc54f9d (marble: parts: Remove play/pause hack while toggling Dolby)
    }

    public boolean getDsOn() {
        boolean on = mDolbyAtmos.getDsOn();
<<<<<<< HEAD
        Log.d(TAG, "getDsOn: " + on);
=======
        Log.i(TAG, "getDsOn: " + on);
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
        return on;
    }

    public void setProfile(int index) {
        checkEffect();
<<<<<<< HEAD
        Log.d(TAG, "setProfile: " + index);
=======
        Log.i(TAG, "setProfile: " + index);
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
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
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
    }
}

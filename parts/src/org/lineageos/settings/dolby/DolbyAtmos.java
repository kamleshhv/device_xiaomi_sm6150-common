/*
 * Copyright (C) 2018 The LineageOS Project
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

import android.media.audiofx.AudioEffect;
import android.util.Log;

import java.util.UUID;

<<<<<<< HEAD
class DolbyAtmos extends AudioEffect {
=======
public class DolbyAtmos extends AudioEffect {
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)

    private static final String TAG = "DolbyAtmos";
    private static final UUID EFFECT_TYPE_DAP =
            UUID.fromString("9d4921da-8225-4f29-aefa-39537a04bcaa");
<<<<<<< HEAD

    private static final int
        EFFECT_PARAM_CPDP_VALUES = 5,
        EFFECT_PARAM_ENABLE = 0,
        EFFECT_PARAM_PROFILE = 0xA000000,
        EFFECT_PARAM_SET_PROFILE_PARAMETER = 0x1000000,
        EFFECT_PARAM_GET_PROFILE_PARAMETER = 0x1000005,
        EFFECT_PARAM_RESET_PROFILE_SETTINGS = 0xC000000;

    enum DsParam {
        HEADPHONE_VIRTUALIZER(101),
        VOLUME_LEVELER(103),
        DIALOGUE_ENHANCER_ENABLE(105),
        DIALOGUE_ENHANCER_AMOUNT(108),
        GEQ(110),
        BASS_ENHANCER(111),
        STEREO_WIDENING(113);

        public int id, length;

        DsParam(int id) {
            this.id = id;
        }

        public int getLength() {
            return (id == GEQ.id) ? 20 : 1;
        }

        public String toString() {
            return String.format("%s(%s)", name(), id);
        }
    }

    DolbyAtmos(int priority, int audioSession) {
        super(EFFECT_TYPE_NULL, EFFECT_TYPE_DAP, priority, audioSession);
    }

    private static int int32ToByteArray(int value, byte[] dst, int index) {
        dst[index++] = (byte) (value & 0xff);
        dst[index++] = (byte) ((value >>> 8) & 0xff);
        dst[index++] = (byte) ((value >>> 16) & 0xff);
        dst[index] = (byte) ((value >>> 24) & 0xff);
        return 4;
    }

    private static int byteArrayToInt32(byte[] ba) {
        return ((ba[3] & 0xff) << 24) | ((ba[2] & 0xff) << 16)
                | ((ba[1] & 0xff) << 8) | (ba[0] & 0xff);
    }

    private static int int32ArrayToByteArray(int[] src, byte[] dst, int index) {
        for (int x : src) {
            dst[index++] = (byte) ((x >>> 0) & 0xff);
            dst[index++] = (byte) ((x >>> 8) & 0xff);
            dst[index++] = (byte) ((x >>> 16) & 0xff);
            dst[index++] = (byte) ((x >>> 24) & 0xff);
        }
        return src.length << 2;
    }

    private static int[] byteArrayToInt32Array(byte[] ba, int dstLength) {
        int srcLength = ba.length >> 2;
        if (dstLength > srcLength) {
            dstLength = srcLength;
        }
        int[] dst = new int[dstLength];
        for (int i = 0; i < dstLength; i++) {
            dst[i] = ((ba[i * 4 + 3] & 0xff) << 24) | ((ba[i * 4 + 2] & 0xff) << 16)
                    | ((ba[i * 4 + 1] & 0xff) << 8) | (ba[i * 4] & 0xff);
        }
        return dst;
=======
    private static final int DAP_PARAM = 5;
    private static final int DAP_PARAM_PROFILE = 0xA000000;
    private static final int DAP_PARAM_VALUE = 0x1000000;
    private static final int DAP_PARAM_GEQ = 110;
    private static final int DAP_PARAM_VOLUME_LEVELER = 103;
    private static final int DAP_PROFILES_COUNT = 9;

    public DolbyAtmos(int priority, int audioSession) {
        super(EFFECT_TYPE_NULL, EFFECT_TYPE_DAP, priority, audioSession);
    }

    private static int int32ToByteArray(int value, byte[] buf, int offset) {
        buf[offset] = (byte) (value & 255);
        buf[offset + 1] = (byte) ((value >>> 8) & 255);
        buf[offset + 2] = (byte) ((value >>> 16) & 255);
        buf[offset + 3] = (byte) ((value >>> 24) & 255);
        return 4;
    }

    private static int byteArrayToInt32(byte[] buf) {
        return (buf[0] & 255) | ((buf[3] & 255) << 24)
                | ((buf[2] & 255) << 16) | ((buf[1] & 255) << 8);
    }

    private static int int32ArrayToByteArray(int[] values, byte[] buf, int offset) {
        for (int value : values) {
            buf[offset] = (byte) ((value >>> 0) & 255);
            buf[offset + 1] = (byte) ((value >>> 8) & 255);
            buf[offset + 2] = (byte) ((value >>> 16) & 255);
            buf[offset + 3] = (byte) ((value >>> 24) & 255);
            offset += 4;
        }
        return values.length << 2;
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
    }

    private void setIntParam(int param, int value) {
        byte[] buf = new byte[12];
        int i = int32ToByteArray(param, buf, 0);
        int32ToByteArray(value, buf, i + int32ToByteArray(1, buf, i));
<<<<<<< HEAD
        checkStatus(setParameter(EFFECT_PARAM_CPDP_VALUES, buf));
=======
        checkStatus(setParameter(DAP_PARAM, buf));
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
    }

    private int getIntParam(int param) {
        byte[] buf = new byte[12];
        int32ToByteArray(param, buf, 0);
<<<<<<< HEAD
        checkStatus(getParameter(EFFECT_PARAM_CPDP_VALUES + param, buf));
        return byteArrayToInt32(buf);
    }

    void setDsOn(boolean on) {
        setIntParam(EFFECT_PARAM_ENABLE, on ? 1 : 0);
        super.setEnabled(on);
    }

    boolean getDsOn() {
        return getIntParam(EFFECT_PARAM_ENABLE) == 1;
    }

    void setProfile(int index) {
        setIntParam(EFFECT_PARAM_PROFILE, index);
    }

    int getProfile() {
        return getIntParam(EFFECT_PARAM_PROFILE);
    }

    void resetProfileSpecificSettings() {
        int profile = getProfile();
        Log.d(TAG, "resetProfileSpecificSettings: profile=" + profile);
        setIntParam(EFFECT_PARAM_RESET_PROFILE_SETTINGS, getProfile());
    }

    void setDapParameter(int profile, DsParam param, int values[]) {
        Log.d(TAG, "setDapParameter: profile=" + profile + " param=" + param.toString());
        int length = values.length;
        byte[] buf = new byte[(length + 4) * 4];
        int i = int32ToByteArray(EFFECT_PARAM_SET_PROFILE_PARAMETER, buf, 0);
        int i2 = i + int32ToByteArray(length + 1, buf, i);
        int i3 = i2 + int32ToByteArray(profile, buf, i2);
        int32ArrayToByteArray(values, buf, i3 + int32ToByteArray(param.id, buf, i3));
        checkStatus(setParameter(EFFECT_PARAM_CPDP_VALUES, buf));
    }

    void setDapParameter(DsParam param, int values[]) {
        setDapParameter(getProfile(), param, values);
    }

    void setDapParameterBool(DsParam param, boolean enable) {
        setDapParameter(param, new int[]{enable ? 1 : 0});
    }

    void setDapParameterInt(DsParam param, int value) {
        setDapParameter(param, new int[]{value});
    }

    int[] getDapParameter(int profile, DsParam param) {
        Log.d(TAG, "getDapParameter: profile=" + profile + " param=" + param.toString());
        int length = param.getLength();
        byte[] buf = new byte[(length + 2) * 4];
        int i = (param.id << 16) + EFFECT_PARAM_GET_PROFILE_PARAMETER;
        checkStatus(getParameter(i + (profile << 8), buf));
        return byteArrayToInt32Array(buf, length);
    }

    int[] getDapParameter(DsParam param) {
        return getDapParameter(getProfile(), param);
    }

    boolean getDapParameterBool(DsParam param) {
        return getDapParameter(param)[0] == 1;
    }

    int getDapParameterInt(DsParam param) {
        return getDapParameter(param)[0];
=======
        checkStatus(getParameter(DAP_PARAM + param, buf));
        return byteArrayToInt32(buf);
    }

    private void setDapParameter(int param, int values[]) {
        for (int profile = 0; profile < DAP_PROFILES_COUNT; profile++) {
            int length = values.length;
            byte[] buf = new byte[(length + 4) * 4];
            int i = int32ToByteArray(DAP_PARAM_VALUE, buf, 0);
            int i2 = i + int32ToByteArray(length + 1, buf, i);
            int i3 = i2 + int32ToByteArray(profile, buf, i2);
            int32ArrayToByteArray(values, buf, i3 + int32ToByteArray(param, buf, i3));
            checkStatus(setParameter(DAP_PARAM, buf));
        }
    }

    public void setDsOn(boolean on) {
        setIntParam(0, on ? 1 : 0);
        super.setEnabled(on);
    }

    public boolean getDsOn() {
        return getIntParam(0) == 1;
    }

    public void setProfile(int index) {
        setIntParam(DAP_PARAM_PROFILE, index);
    }

    public int getProfile() {
        return getIntParam(DAP_PARAM_PROFILE);
    }

    public void setGeqBandGains(int[] gains) {
        setDapParameter(DAP_PARAM_GEQ, gains);
    }

    public void setVolumeLevelerEnabled(boolean enable) {
        setDapParameter(DAP_PARAM_VOLUME_LEVELER, new int[]{enable ? 1 : 0});
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
    }
}

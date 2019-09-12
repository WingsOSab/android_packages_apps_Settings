/*
 * Copyright (C) 2024 The LineageOS Project
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

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.annotation.VisibleForTesting;

import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;

public class WingsVersionPreferenceController extends BasePreferenceController {

    @VisibleForTesting
    static final String WINGS_VERSION_PROPERTY = "ro.wings.base.version";
    static final String WINGS_BUILDTYPE_PROPERTY = "ro.wings.buildtype";
    static final String WINGS_EDITION_PROPERTY = "ro.wings.edition";

    public WingsVersionPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return !TextUtils.isEmpty(SystemProperties.get(WINGS_VERSION_PROPERTY)) && !TextUtils.isEmpty(SystemProperties.get(WINGS_BUILDTYPE_PROPERTY)) && !TextUtils.isEmpty(SystemProperties.get(WINGS_EDITION_PROPERTY))
                ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public CharSequence getSummary() {
        String wingsVersion = SystemProperties.get(WINGS_VERSION_PROPERTY);
        String wingsBuildType = SystemProperties.get(WINGS_BUILDTYPE_PROPERTY);
        String wingsEdition = SystemProperties.get(WINGS_EDITION_PROPERTY);
        if (!wingsVersion.isEmpty() && !wingsBuildType.isEmpty() && !wingsEdition.isEmpty()) {
            return wingsVersion + " | " + wingsBuildType + " | " + wingsEdition;
        } else {
            return
                mContext.getString(R.string.device_info_default);
        }
    }
}

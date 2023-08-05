/*
 * Copyright (C) 2020 Wave-OS
 * Copyright (C) 2021 ShapeShiftOS
 * Copyright (C) 2024 WingsOS
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

package com.android.settings.deviceinfo.aboutphone;

import java.io.IOException;
import android.content.Context;
import android.os.SystemProperties;
import android.widget.TextView;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import androidx.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.utils.WingsSpecUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.LayoutPreference;
import com.android.settingslib.Utils;
import com.android.settings.core.PreferenceControllerMixin;

public class WingsInfoPreferenceController extends AbstractPreferenceController {

    private static final String KEY_WINGS_INFO = "wings_info";

    public WingsInfoPreferenceController(Context context) {
        super(context);
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        final LayoutPreference wingsInfoPreference = screen.findPreference(KEY_WINGS_INFO);
        final TextView processor = (TextView) wingsInfoPreference.findViewById(R.id.processor_message);
        final TextView storage = (TextView) wingsInfoPreference.findViewById(R.id.storage_code_message);
        final TextView battery = (TextView) wingsInfoPreference.findViewById(R.id.battery_type_message);
        final TextView infoScreen = (TextView) wingsInfoPreference.findViewById(R.id.screen_message);
        processor.setText(WingsSpecUtils.getProcessorModel());
        storage.setText(String.valueOf(WingsSpecUtils.getTotalInternalMemorySize()) + "GB ROM + " + WingsSpecUtils.getTotalRAM() + " RAM");
        battery.setText(WingsSpecUtils.getBatteryCapacity(mContext) + " mAh");
        infoScreen.setText(WingsSpecUtils.getScreenRes(mContext));
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getPreferenceKey() {
        return KEY_WINGS_INFO;
    }
}

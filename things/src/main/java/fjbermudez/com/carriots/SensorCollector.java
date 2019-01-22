/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fjbermudez.com.carriots;

import java.util.List;

/**
 * Abstracts the process of reading sensor data from related sensors.
 */
public interface SensorCollector {
    boolean activate();
    void setEnabled(String sensor, boolean enabled);
    boolean isEnabled(String sensor);
    List<String> getAvailableSensors();
    List<String> getEnabledSensors();
    void collectRecentReadings(List<SensorData> output);
    void closeQuietly();
}
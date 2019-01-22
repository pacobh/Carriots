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

public class SensorData {
    private long timestamp;
    private String sensorName;
    private float value;

    public SensorData(String sensorName, float value) {
        this(System.currentTimeMillis(), sensorName, value);
    }

    public SensorData(long timestamp, String sensorName, float value) {
        this.timestamp = timestamp;
        this.sensorName = sensorName;
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSensorName() {
        return sensorName;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return sensorName + " [" + timestamp + "] " + value;
    }
}
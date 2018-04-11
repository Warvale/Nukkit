/*
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (C) 2018 NukkitX Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * verion 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * Contact info: info@nukkitx.com
 */

package cn.nukkit.server.level.util;


import com.google.common.base.Preconditions;

public class NibbleArray implements Cloneable {
    private final byte[] data;

    public NibbleArray(int length) {
        data = new byte[length / 2];
    }

    public NibbleArray(byte[] array) {
        data = array;
    }

    public byte get(int index) {
        Preconditions.checkElementIndex(index, data.length * 2);
        byte val = data[index / 2];
        if (index % 2 == 0) {
            return (byte) (val & 0x0f);
        } else {
            return (byte) ((val & 0xf0) >> 4);
        }
    }

    public void set(int index, byte value) {
        Preconditions.checkArgument(value >= 0 && value < 16, "Nibbles must have a value between 0 and 15.");
        Preconditions.checkElementIndex(index, data.length * 2);
        value &= 0xf;
        int half = index / 2;
        byte previous = data[half];
        if (index % 2 == 0) {
            data[half] = (byte) (previous & 0xf0 | value);
        } else {
            data[half] = (byte) (previous & 0x0f | value << 4);
        }
    }

    public void fill(byte value) {
        Preconditions.checkArgument(value >= 0 && value < 16, "Nibbles must have a value between 0 and 15.");
        value &= 0xf;
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) ((value << 4) | value);
        }
    }

    public void copyFrom(byte[] bytes) {
        Preconditions.checkNotNull(bytes, "bytes");
        Preconditions.checkArgument(bytes.length == data.length, "length of provided byte array is %s but expected %s", bytes.length,
                data.length);
        System.arraycopy(bytes, 0, data, 0, data.length);
    }

    public void copyFrom(NibbleArray array) {
        Preconditions.checkNotNull(array, "array");
        copyFrom(array.data);
    }

    public byte[] getData() {
        return data;
    }

    public NibbleArray copy() {
        return new NibbleArray(getData().clone());
    }
}

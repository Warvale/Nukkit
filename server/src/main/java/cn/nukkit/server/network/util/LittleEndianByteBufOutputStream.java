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

package cn.nukkit.server.network.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

public class LittleEndianByteBufOutputStream extends ByteBufOutputStream {

    public LittleEndianByteBufOutputStream(ByteBuf buffer) {
        super(buffer);
    }

    @Override
    public void writeShort(int val) {
        buffer().writeShortLE(val);
    }

    @Override
    public void writeLong(long val) {
        buffer().writeLongLE(val);
    }

    @Override
    public void writeInt(int val) {
        buffer().writeIntLE(val);
    }
}

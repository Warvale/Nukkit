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

package cn.nukkit.api.scheduler;

import cn.nukkit.api.plugin.Plugin;

/**
 * @author CreeperFace
 */
public interface NukkitScheduler {

    TaskHandler scheduleTask(Plugin plugin, Runnable task);

    TaskHandler scheduleRepeatingTask(Plugin plugin, Runnable task, int period);

    TaskHandler scheduleDelayedTask(Plugin plugin, Runnable task, int delay);

    TaskHandler scheduleDelayedRepeatingTask(Plugin plugin, Runnable task, int delay, int period);

    TaskHandler scheduleAsyncTask(Plugin plugin, Runnable task);

    void cancelTask(int taskId);

    void cancelAllTasks();

    void cancelTasks(Plugin plugin);

    boolean isQueued(int taskId);
}

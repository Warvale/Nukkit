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

package cn.nukkit.api.entity.system;

import cn.nukkit.api.entity.Entity;
import cn.nukkit.api.entity.component.EntityComponent;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class System {
    private final Set<Class<? extends EntityComponent>> expectedComponents;
    private final SystemRunner runner;

    private System(Set<Class<? extends EntityComponent>> expectedComponents, SystemRunner runner) {
        this.expectedComponents = expectedComponents;
        this.runner = runner;
    }

    public static Builder builder() {
        return new Builder();
    }

    public final Set<Class<? extends EntityComponent>> getExpectedComponents() {
        return expectedComponents;
    }

    public final SystemRunner getRunner() {
        return runner;
    }

    public final boolean isSystemCompatible(Entity entity) {
        for (Class<? extends EntityComponent> component : expectedComponents) {
            if (!entity.providedComponents().contains(component)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        System system = (System) o;
        return Objects.equals(expectedComponents, system.expectedComponents) &&
                Objects.equals(runner, system.runner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expectedComponents, runner);
    }

    @Override
    public String toString() {
        return "System{" +
                "expectedComponents=" + expectedComponents +
                ", runner=" + runner +
                '}';
    }

    public static class Builder {
        private final ImmutableSet.Builder<Class<? extends EntityComponent>> expectedComponents = ImmutableSet.builder();
        private SystemRunner runner;

        private Builder() {

        }

        public final Builder expectComponent(Class<? extends EntityComponent> component) {
            expectedComponents.add(component);
            return this;
        }

        @SafeVarargs
        public final Builder expectComponents(Class<? extends EntityComponent>... components) {
            expectedComponents.addAll(Arrays.asList(components));
            return this;
        }

        public final Builder runner(SystemRunner runner) {
            this.runner = runner;
            return this;
        }

        public final System build() {
            Preconditions.checkArgument(runner != null, "no runner set");
            return new System(expectedComponents.build(), runner);
        }
    }
}
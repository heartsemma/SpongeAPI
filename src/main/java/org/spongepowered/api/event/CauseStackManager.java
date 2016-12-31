/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.event;

import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;

import java.util.Optional;

/**
 * Provides an interface into the system managing the cause and contextual
 * information for events being thrown. This allows plugins to attach more
 * contextual information to events which may be triggered from their api
 * interactions.
 * 
 * <p>For example a plugin may wish to push a player into the cause stack before
 * spawning an entity to expose to any plugin listening for SpawnEntityEvents
 * that the player is the reason that the entity was spawned.</p>
 * 
 * <p>This system will automatically handle associating a plugin with actions
 * performed inside of event listeners, command executors, and scheduled
 * tasks.</p>
 */
public interface CauseStackManager {

    /**
     * Gets the current {@link Cause} object from the current cause stack.
     * 
     * @return A cause of the current stack.
     */
    Cause getCurrentCause();

    /**
     * Gets an {@link EventContext} object on the current contextual
     * information.
     * 
     * @return The current event context
     */
    EventContext getCurrentContext();

    /**
     * Pushes an object to the current cause stack which will associate it with
     * all events through from api actions until it is popped off again.
     * 
     * @param obj The object to push to the stack
     */
    void pushCause(Object obj);

    /**
     * Pops the most recently pushed cause object off of the stack and returns
     * it.
     * 
     * @return The last pushed object
     */
    Object popCause();

    /**
     * Pushes a frame of the current cause stack and context state.
     */
    void pushCauseFrame();

    /**
     * Replaces the current cause stack and context with the cause frame at the
     * top of the frame stack.
     */
    void popCauseFrame();

    /**
     * Adds the given object to the current context under the given key.
     * 
     * @param key The context key
     * @param value The object
     * @see EventContextKeys
     */
    void addContext(String key, Object value);

    /**
     * Clears the given context key from the current context.
     * 
     * @param key The key to clear
     * @return The existing context value, if it was present
     * @see EventContextKeys
     */
    Optional<?> clearContext(String key);

}

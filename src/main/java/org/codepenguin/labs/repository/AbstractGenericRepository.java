/*
 * MIT License
 *
 * Copyright (c) 2022 CodePenguin.org - Jorge Alfonso Garcia Espinosa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package org.codepenguin.labs.repository;

import com.google.gson.Gson;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An abstract generic repository to read JSON files from the resources directory and find the data.
 *
 * @param <T> the type of the entity.
 * @param <I> the type of the id.
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
abstract class AbstractGenericRepository<T, I> implements GenericRepository<T, I> {

    private final Map<?, ?> dataMap;

    /**
     * Constructor.
     *
     * @param resource the file resource.
     * @throws IOException If an I/O error occurs.
     */
    AbstractGenericRepository(final Resource resource) throws IOException {
        dataMap = read(resource);
    }

    /**
     * Finds a map with the data by the id.
     *
     * @param id the id.
     * @return a map with the data, if it's found; otherwise, {@link Optional#empty()}
     */
    @SuppressWarnings("rawtypes")
    protected Optional<Map> findData(final I id) {
        return ((List<?>) dataMap.get("data")).stream()
                .map(Map.class::cast)
                .filter(map -> filter(map, id))
                .findFirst();
    }

    /**
     * Finds a single property data by the id.
     *
     * @param id       the id.
     * @param property the property name.
     * @return the single value, if it's found; otherwise, {@link Optional#empty()}
     */
    protected Optional<T> findSingleData(final I id, final String property) {
        final var data = findData(id);
        if (data.isEmpty()) {
            return Optional.empty();
        }

        final var value = data.get().get(property);
        //noinspection unchecked
        return value == null ? Optional.empty() : Optional.of((T) value);
    }

    @SuppressWarnings("rawtypes")
    private Map read(final Resource resource) throws IOException {
        try (var reader = new InputStreamReader(resource.getInputStream())) {
            return Collections.unmodifiableMap(new Gson().fromJson(reader, Map.class));
        }
    }

    private boolean filter(final Map<?, ?> map, final I id) {
        final var mapId = (Double) map.get("id");
        return id instanceof Long longId ? mapId.longValue() == longId : mapId.equals(id);
    }
}

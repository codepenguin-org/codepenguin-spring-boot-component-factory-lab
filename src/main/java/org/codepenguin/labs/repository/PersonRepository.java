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

import org.codepenguin.labs.enums.Country;
import org.codepenguin.labs.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

/**
 * Repository for persons.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@Repository
public final class PersonRepository extends AbstractGenericRepository<Person, Long> {

    /**
     * Constructor.
     *
     * @param resource the file resource.
     * @throws IOException If an I/O error occurs.
     */
    @Autowired
    public PersonRepository(@Value("classpath:data/persons.json") final Resource resource) throws IOException {
        super(resource);
    }

    @Override
    public Optional<Person> find(final Long id) {
        final var response = findData(id);
        if (response.isEmpty()) {
            return Optional.empty();
        }

        final var map = response.get();
        return Optional.of(new Person(
                ((Double) map.get("id")).longValue(),
                (String) map.get("first"),
                (String) map.get("last"),
                Country.valueOf((String) (map.get("nat"))))
        );
    }
}

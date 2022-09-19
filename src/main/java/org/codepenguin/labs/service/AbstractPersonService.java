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

package org.codepenguin.labs.service;

import org.codepenguin.labs.entity.Person;
import org.codepenguin.labs.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

/**
 * Abstract service for Persons that finds its data and expenses.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
abstract class AbstractPersonService implements PersonService {

    protected final GenericRepository<Person, Long> personRepository;
    protected final GenericRepository<Double, Long> expenseRepository;

    /**
     * Constructor.
     *
     * @param personRepository  the persons' repository.
     * @param expenseRepository the expenses' repository.
     */
    @Autowired
    AbstractPersonService(@Qualifier("personRepository") final GenericRepository<Person, Long> personRepository,
                          @Qualifier("expenseRepository") final GenericRepository<Double, Long> expenseRepository) {
        this.personRepository = personRepository;
        this.expenseRepository = expenseRepository;
    }

    /**
     * Fills the person with new data.
     *
     * @param person the person.
     */
    protected abstract void fillPerson(Person person);

    @Override
    public Optional<Person> find(final Long id) {
        final var response = personRepository.find(id);
        if (response.isEmpty()) {
            return response;
        }

        final var person = response.get();
        expenseRepository.find(id).ifPresent(person::setExpenses);
        fillPerson(person);
        return Optional.of(person);
    }


}

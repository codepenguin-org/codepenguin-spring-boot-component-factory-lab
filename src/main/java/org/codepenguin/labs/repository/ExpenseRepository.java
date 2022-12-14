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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

/**
 * Repository for person's expenses.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@Repository
public final class ExpenseRepository extends AbstractGenericRepository<Double, Long> {

    /**
     * Constructor.
     *
     * @param resource the file resource.
     * @throws IOException If an I/O error occurs.
     */
    @Autowired
    public ExpenseRepository(@Value("classpath:data/expenses.json") final Resource resource) throws IOException {
        super(resource);
    }

    @Override
    public Optional<Double> find(final Long id) {
        return findSingleData(id, "expense");
    }
}

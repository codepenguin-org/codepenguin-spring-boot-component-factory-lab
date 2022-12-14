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

package org.codepenguin.labs.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.codepenguin.labs.enums.Country;

/**
 * Person.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@Schema(description = "Person.")
@Data
public final class Person {

    @Schema(description = "the id.")
    private Long id;

    @Schema(description = "the first name.")
    private String firstName;

    @Schema(description = "the last name.")
    private String lastName;

    @Schema(description = "the country.")
    private Country country;

    @Schema(description = "the USD expenses.")
    private Double expenses;

    @Schema(description = "the VIP status.")
    private Boolean vip;

    @Schema(description = "the land area in m2.")
    private Double landArea;

    /**
     * Constructor.
     *
     * @param id        the id.
     * @param firstName the first name.
     * @param lastName  the last name.
     * @param country   the country.
     */
    public Person(final Long id, final String firstName, final String lastName, final Country country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}

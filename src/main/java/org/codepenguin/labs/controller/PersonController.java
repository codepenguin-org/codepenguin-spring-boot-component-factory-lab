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

package org.codepenguin.labs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.codepenguin.labs.enums.Country;
import org.codepenguin.labs.entity.Person;
import org.codepenguin.labs.service.PersonServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for persons.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonServiceFactory personServiceFactory;

    /**
     * Constructor.
     *
     * @param personServiceFactory the person service factory.
     */
    @Autowired
    public PersonController(final PersonServiceFactory personServiceFactory) {
        this.personServiceFactory = personServiceFactory;
    }

    /**
     * Finds a person data by country and id.
     *
     * @param country the country.
     * @param id      the id.
     * @return an HTTP 200 response with the found person and its data according to the country or an HTTP 404 response
     * if not found.
     */
    @Operation(summary = "Finds a person data by country and id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the person.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Person not found.", content = @Content)
    })
    @GetMapping("/{country}/{id}")
    public ResponseEntity<Person> find(@Parameter(description = "the country") @PathVariable final Country country,
                                       @Parameter(description = "the id") final @PathVariable Long id) {
        final var response = personServiceFactory.get(country).find(id);
        return response.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(response.get());

    }
}

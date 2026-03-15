package com.zanosov.infrastructure.web.admin.controller.language;

import com.zanosov.domain.PageResult;
import com.zanosov.infrastructure.web.admin.dto.LanguageDto;
import com.zanosov.infrastructure.web.admin.facade.LanguageFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class LanguageAdminController {

    private final LanguageFacade languageFacade;

    public LanguageAdminController(LanguageFacade languageFacade) {
        this.languageFacade = languageFacade;
    }

    @PostMapping("/admin/languages")
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageDto create(@Valid @RequestBody CreateLanguageRequest request) {
        return languageFacade.create(request);
    }

    @DeleteMapping("/admin/languages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        languageFacade.deleteById(id);
    }

    @GetMapping("/admin/languages/{id}")
    public LanguageDto getById(@PathVariable UUID id) {
        return languageFacade.findById(id);
    }

    @PutMapping("/admin/languages/{id}")
    public LanguageDto update(@PathVariable UUID id, @Valid @RequestBody UpdateLanguageRequest request) {
        return languageFacade.update(id, request);
    }

    @GetMapping("/admin/languages")
    public PageResult<LanguageDto> listOrderedByPosition(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return languageFacade.listOrderedByPosition(page, size);
    }
}

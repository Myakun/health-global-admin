package com.zanosov.infrastructure.web.admin.controller.language;

import com.zanosov.domain.PageResult;
import com.zanosov.infrastructure.web.admin.dto.LanguageDto;
import com.zanosov.infrastructure.web.admin.facade.LanguageFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class LanguageAdminController {

    private final LanguageFacade languageFacade;

    public LanguageAdminController(LanguageFacade languageFacade) {
        this.languageFacade = languageFacade;
    }

    @GetMapping("/admin/languages")
    public PageResult<LanguageDto> listOrderedByPosition(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return languageFacade.listOrderedByPosition(page, size);
    }
}

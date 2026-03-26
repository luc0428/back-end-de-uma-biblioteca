package com.facens.app_biblioteca_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.facens.app_biblioteca_api.model.Livro;
import com.facens.app_biblioteca_api.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public
}

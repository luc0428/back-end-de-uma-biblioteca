package com.facens.app_biblioteca_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.facens.app_biblioteca_api.model.Livro;
import com.facens.app_biblioteca_api.service.LivroService;

@RestController // É a combinação do Controller com o ResponseBody
@RequestMapping("/livros") // É utilizado para mapear as URL's para métodos

public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarTodos();
    }

    @GetMapping("/teste")
    public String testeAPI() {
        return "API funcionando corretamente";
    }

    @GetMapping("/{id}") // o {id} é utilizado para mapear uma requisição
    public Livro buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro criarLivro(@RequestBody Livro livro) {
        return livroService.criar(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        return livroService.atualizar(id, livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerLivro(@PathVariable Long id) {
        livroService.deletar(id);
    }

    @PutMapping("/{id}/emprestar")
    public Livro emprestaLivro(@PathVariable Long id) {
        return livroService.emprestar(id);
    }

    @PutMapping("/{id}/devolver")
    public Livro devolverLivro(@PathVariable Long id) {
        return livroService.devolver(id);
    }
}
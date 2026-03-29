package com.facens.app_biblioteca_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.facens.app_biblioteca_api.model.Livro;
import com.facens.app_biblioteca_api.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id){
        return  livroRepository.findById(id).orElseThrow(()->new 
        ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    public Livro criar(Livro livro){
        livro.setId( null);
        if (livro.getEmprestado() == null){
            livro.setEmprestado(false);
        }
        if(Boolean.FALSE.equals(livro.getEmprestado())){
            livro.setDataEmprestimo(null);
        }
        return livroRepository.save(livro);
    }

    public Livro atualizar(Long id, Livro livroAtualizando){
        Livro livroExistente = buscarPorId(id);
            livroExistente.setTitulo(livroAtualizando.getTitulo());
            livroExistente.setAutor(livroAtualizando.getAutor());

            if (livroAtualizando.getEmprestado() != null) {
                    livroExistente.setEmprestado(livroAtualizando.getEmprestado());
                    
                    if (Boolean.TRUE.equals(livroAtualizando.getEmprestado()) && livroAtualizando.getDataEmprestimo() == null) {
                        livroExistente.setDataEmprestimo(LocalDate.now());
                    }else if (Boolean.FALSE.equals(LocalDate.now())) {
                        livroExistente.setDataEmprestimo(null);
                    } else {
                        livroExistente.setDataEmprestimo(livroAtualizando.getDataEmprestimo());
                    }
            }
            return  livroRepository.save(livroExistente);
    }

    public void deletar(Long id){
        Livro livro = buscarPorId(id);
        livroRepository.delete(livro);
    }

    public Livro emprestar(Long id){
        Livro livro = buscarPorId(id);
        if (Boolean.TRUE.equals(livro.getEmprestado())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Livro ja está emprestado");
        }
        livro.setEmprestado(true);
        livro.setDataEmprestimo(LocalDate.now());
        return livroRepository.save(livro);
    }

    
    public Livro devolver(Long id){
        Livro livro = buscarPorId(id);
        if (Boolean.FALSE.equals(livro.getEmprestado())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Livro ja está emprestado");
        }
        livro.setEmprestado(false);
        livro.setDataEmprestimo(null);
        return livroRepository.save(livro);
    }
}

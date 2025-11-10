package br.edu.insper.exercicio.filmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> getFilmes() {
        return filmeRepository.findAll();
    }

    public Filme createFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public Filme getFilme(Integer id) {
        Optional<Filme> f = filmeRepository.findById(id);
        return f.orElse(null);
    }

    public void deleteFilme(Integer id) {
        filmeRepository.deleteById(id);
    }
}

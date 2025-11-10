package br.edu.insper.exercicio.filmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> getFilmes() {
        return filmeService.getFilmes();
    }

    @PostMapping
    public ResponseEntity<Filme> createFilme(@Valid @RequestBody Filme filme) {
        if (filme.getNota() != null && (filme.getNota() < 0 || filme.getNota() > 5)) {
            return ResponseEntity.badRequest().build();
        }
        Filme saved = filmeService.createFilme(filme);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilme(@PathVariable Integer id) {
        Filme f = filmeService.getFilme(id);
        if (f == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(f);
    }

    // Somente administradores podem excluir
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Integer id) {
        Filme f = filmeService.getFilme(id);
        if (f == null) return ResponseEntity.notFound().build();
        filmeService.deleteFilme(id);
        return ResponseEntity.noContent().build();
    }
}

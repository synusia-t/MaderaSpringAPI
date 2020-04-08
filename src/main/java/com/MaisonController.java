package com;

import com.model.Maison;
import com.model.MaisonRepository;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
@RestController
public class MaisonController {

    @Autowired
    private MaisonRepository maisonRepository;

    @PostMapping("/maison")
    public ResponseEntity<Maison> addNewMaison(@RequestParam String lat,
                                             @RequestParam String lng,
                                             @RequestParam String dateLivraison,
                                             @RequestParam String adresse,
                                             @RequestParam String codePostal,
                                             @RequestParam String ville

    ) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date livraison = dateFormat.parse(dateLivraison);
        Maison maison = new Maison();
        maison.setLat(lat);
        maison.setLng(lng);
        maison.setDateLivraison(livraison);
        maison.setAdresse(adresse);
        maison.setCodePostal(codePostal);
        maison.setVille(ville);
        maisonRepository.save(maison);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(maison.getId())
                .toUri();
        return ResponseEntity.created(uri).body(maison);

    }

    @GetMapping("/maisons")
    public @ResponseBody
    Iterable<Maison> getAllMaisons() {
        return maisonRepository.findAll();
    }

    @GetMapping("/maison/{id}")
    public @ResponseBody
    Optional<Maison> getMaison(@PathVariable int id) {
        return  maisonRepository.findById(id);
    }

    @DeleteMapping("/maison/{id}")
    public String deleteMaison(@PathVariable int id) {
        maisonRepository.delete(maisonRepository.findById(id).get());
        return  "maison :"+id+" deleted";
    }
}

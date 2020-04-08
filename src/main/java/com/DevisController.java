package com;

import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
@RestController
public class DevisController {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MaisonRepository maisonRepository;

    @PostMapping("/devis")
    public ResponseEntity<Devis> addNewDevis(@RequestParam String nom,
                                               @RequestParam int numero,
                                               @RequestParam int client_id,
                                               @RequestParam int commercial_id,
                                               @RequestParam int maison_id,
                                               @RequestParam String dateEstimee

    ) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date estimated_at = dateFormat.parse(dateEstimee);

        User client = userRepository.findById(client_id).get();
        User commercial = userRepository.findById(commercial_id).get();
        Maison maison = maisonRepository.findById(maison_id).get();

        Devis devis = new Devis();
        devis.setNom(nom);
        devis.setNumero(numero);
        devis.setClient(client);
        devis.setCommercial(commercial);
        devis.setDateEstimee(estimated_at);
        devis.setDateCreation(new Date());
        devis.setMaison(maison);

        devisRepository.save(devis);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(devis.getId())
                .toUri();
        return ResponseEntity.created(uri).body(devis);

    }

    @GetMapping("/devis")
    public @ResponseBody
    Iterable<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    @GetMapping("/devis/{id}")
    public @ResponseBody
    Optional<Devis> getDevis(@PathVariable int id) {
        return  devisRepository.findById(id);
    }

    @DeleteMapping("/devis/{id}")
    public String deleteDevis(@PathVariable int id) {
        devisRepository.delete(devisRepository.findById(id).get());
        return  "devis :"+id+" deleted";
    }
}

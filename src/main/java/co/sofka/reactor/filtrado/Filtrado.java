package co.sofka.reactor.filtrado;

import co.sofka.reactor.ReactorApplication;
import co.sofka.reactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {
    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void filter() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .filter(p -> p.getEdad() > 28)
                .subscribe(p -> log.info(p.toString()));
    }

    public void distinct() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(1, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .distinct()
                .subscribe(p -> log.info(p.toString()));
    }

    public void take() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .take(1)
                //.takelast
                //.skip
                //.skiplast
                .subscribe(p -> log.info(p.toString()));

    }



    }

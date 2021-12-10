package co.sofka.reactor.matematicos;

import co.sofka.reactor.ReactorApplication;
import co.sofka.reactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Matematico {
    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void average() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 21));
        personas.add(new Persona(2, "jesus", 22));
        personas.add(new Persona(3, "pedro", 23));

        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(p -> log.info(p.toString()));
    }

}

package co.sofka.reactor.condicionales;

import co.sofka.reactor.ReactorApplication;
import co.sofka.reactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Condicional {
    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void defaultIfEmpty() {
        Mono.empty()
                .defaultIfEmpty(new Persona(0, "Defecto", 99))
                .subscribe(x -> log.info(x.toString()));
    }

    public void takeUntil() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 21));
        personas.add(new Persona(2, "jesus", 22));
        personas.add(new Persona(3, "pedro", 23));

        Flux.fromIterable(personas)
                .takeUntil(p -> p.getEdad() > 21)
                .subscribe(x -> log.info(x.toString()));
    }

    public void timeout() throws InterruptedException {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 21));
        personas.add(new Persona(2, "jesus", 22));
        personas.add(new Persona(3, "pedro", 23));

        Flux.fromIterable(personas)
                .delayElements(Duration.ofSeconds(3))
                .timeout(Duration.ofSeconds(2))
                .subscribe(x -> log.info(x.toString()));
        Thread.sleep(10000);
    }
}

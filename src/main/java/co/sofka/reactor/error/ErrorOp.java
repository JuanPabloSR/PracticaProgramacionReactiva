package co.sofka.reactor.error;

import co.sofka.reactor.ReactorApplication;
import co.sofka.reactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;

public class ErrorOp {
    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void retry() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .retry(2)
                .doOnNext(x -> log.info(x.toString()))
                .subscribe();
    }
    public void errorReturn() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorReturn(new Persona(0, "XYZ", 99))
                .subscribe(x -> log.info(x.toString()));
    }
    public void errorResumen() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorResume(e -> Mono.just(new Persona(0, "XDXDD", 666)))
                .subscribe(x -> log.info(x.toString()));
    }

    public void errorMap() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorMap(e -> new InterruptedException(e.getMessage()))
                .subscribe(x -> log.info(x.toString()));
    }

}

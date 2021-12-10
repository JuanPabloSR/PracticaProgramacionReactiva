package co.sofka.reactor.creacion;

import co.sofka.reactor.ReactorApplication;
import co.sofka.reactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class Creacion {
    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void justFrom() {
        Mono.just(new Persona(1, "sebitas", 25))
                .repeat(5)
                .subscribe(p -> log.info(p.toString()));
        //Flux.fromIterable(coleccion);
        //Observable.just(item);
    }

    public void empty() {
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    public void range() {
        Flux.range(0, 10)
                .doOnNext(i -> log.info("i : " + i))
                .subscribe();
    }

    public void repeat() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 28));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .repeat(3)
                .subscribe(p -> log.info(p.toString()));
    }

}

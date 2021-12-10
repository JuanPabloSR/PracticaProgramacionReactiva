package co.sofka.reactor.combinaciones;

import co.sofka.reactor.ReactorApplication;
import co.sofka.reactor.model.Persona;
import co.sofka.reactor.model.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {
    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void merge() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "pikon", 43));
        personas2.add(new Persona(5, "kevin", 50));
        personas2.add(new Persona(6, "yiii", 70));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.merge(fx1, fx2, fx3)
                .subscribe(p -> log.info(p.toString()));

    }

    public void zip() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 30));
        personas.add(new Persona(3, "pedro", 20));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "pikon", 43));
        personas2.add(new Persona(5, "kevin", 50));
        personas2.add(new Persona(6, "yiii", 70));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.zip(fx1, fx2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s", p1, p2))
                .subscribe(x -> log.info(x));

    }
}
package co.sofka.reactor.transformacion;

import co.sofka.reactor.ReactorApplication;
import co.sofka.reactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {
    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void map() {
       /** List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 28));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .map(p -> {
                    p.setEdad(p.getEdad() + 10);
                    p.setNombres(p.getNombres() + " papulince 100% real no fake");
                    return p;
                })
                .subscribe(p -> log.info(p.toString()));
        */
       Flux<Integer> fx = Flux.range(0, 10);
       Flux<Integer> fx2= fx.map(x -> x + 10);
       fx2.subscribe(x -> log.info("X : " + x));
    }
    public void flatMap() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 28));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(p -> log.info(p.toString()));
    }

    public void groupBy() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 28));
        personas.add(new Persona(1, "pedro", 20));

        Flux.fromIterable(personas)
                .groupBy(Persona :: getIdPersona)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(x -> log.info(x.toString()));
    }

}

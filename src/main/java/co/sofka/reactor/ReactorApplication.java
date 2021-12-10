package co.sofka.reactor;

import co.sofka.reactor.combinaciones.Combinacion;
import co.sofka.reactor.condicionales.Condicional;
import co.sofka.reactor.creacion.Creacion;
import co.sofka.reactor.error.ErrorOp;
import co.sofka.reactor.filtrado.Filtrado;
import co.sofka.reactor.matematicos.Matematico;
import co.sofka.reactor.transformacion.Transformacion;
import org.slf4j.LoggerFactory;
import co.sofka.reactor.model.Persona;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactorApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void reactor() {
        Mono.just(new Persona(1, "juan", 22))
                .doOnNext(p -> {
                    //se puede colocar logica adicional
                    log.info("[Reactor] Persona: " + p);
                })
                .subscribe(p -> log.info("[Reactor] Persona: " + p));
    }

    public void rxjava3() {
        Observable.just(new Persona(1, "juan", 22))
                .doOnNext(p -> log.info("[Reactor] Persona: " + p))
                .subscribe(p -> log.info("[RxJava3] Persona: " + p));
    }

    public void mono() {
        Mono.just(new Persona(1, "juan", 22)).subscribe(p -> log.info(p.toString() + " ejemplo Mono"));
    }
    public void flux(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 28));
        personas.add(new Persona(3, "pedro", 20));

        Flux.fromIterable(personas).subscribe(p -> log.info(p.toString() +  " ejemplo Flux"));
    }

    public void fluxMono() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "juan", 23));
        personas.add(new Persona(2, "jesus", 28));
        personas.add(new Persona(3, "pedro", 20));

        Flux<Persona> fx = Flux.fromIterable(personas);
        fx.collectList().subscribe(lista -> log.info(lista.toString()));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Matematico app = new Matematico();  // hacemos una instancia de la clase
        app.average();
    }
}

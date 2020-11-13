package uni.eszterhazy.keretrendszer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;
import uni.eszterhazy.keretrendszer.service.DolgozoService;

import java.awt.*;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/")
public class RESTDolgozoController {

    @Autowired
    DolgozoService dolgozoService;

    //    @GetMapping(value = "dolgozok")
//    public Collection<Dolgozo> getAllDolgozo(){
//        return dolgozoService.getAllDolgozo();
//    }
//2561ea85-4e9d-4d33-b56f-8f8053cc0fce
    @GetMapping(value = "dolgozo/{id:[A-Za-z0-9]{8}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{12}}")
    public Dolgozo getDolgozoById(@PathVariable(name = "id") String id) {
        return dolgozoService.getDolgozoById(id);
    }

   /* @GetMapping(value = "dolgozok")
    public Collection<Dolgozo> getAllDolgozoOfReszleg(@RequestParam(value = "reszleg", required = false) Reszleg reszleg, @RequestParam(value = "minimumfizetes", required = false) Integer fizetes) {
        int fizetesErtek;
        if(fizetes==null){
            fizetesErtek=0;
        }else{
            fizetesErtek = fizetes.intValue();
        }
        if (reszleg != null && fizetesErtek==0) {
            return dolgozoService.readAllDolgozoOfReszleg(reszleg);
        }
        if(reszleg != null && fizetesErtek > 0){
            return dolgozoService.readAllDolgozoOfReszleg(reszleg).stream().filter(d -> d.getFizetes()> fizetes ).collect(Collectors.toList());
        }
        if(reszleg == null && fizetesErtek > 0){
            return dolgozoService.getAllDolgozo().stream().filter(d -> d.getFizetes() > fizetes).collect(Collectors.toList());
        }
        return dolgozoService.getAllDolgozo();
    }*/


    @GetMapping(value = "dolgozok")
    public Collection<Dolgozo> getAllDolgozoOfReszleg(@RequestParam(value = "reszleg", required = false) Reszleg reszleg, @RequestParam(value = "minimumfizetes", required = false) Integer fizetes) {
        Collection<Dolgozo> fizetesszerint= fizetes!=null? dolgozoService.getAllDolgozoWithMinimumWage(fizetes.intValue()): dolgozoService.getAllDolgozo();
        Collection<Dolgozo> reszlegszerint = reszleg !=null?dolgozoService.readAllDolgozoOfReszleg(reszleg): dolgozoService.getAllDolgozo();
        return fizetesszerint.stream().filter(d -> reszlegszerint.contains(d)).collect(Collectors.toList());

    }

    @PostMapping(value = "dolgozok", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json;charset=utf-8")
    public String addDolgozo(@RequestBody Dolgozo dolgozo) throws DolgozoAlreadyAdded {
        System.out.println("Hozzáadandó: "+dolgozo);
        dolgozoService.addDolgozo(dolgozo);
        return "Új dolgozó került hozzáadásra a következő azonosítóval: "+dolgozo.getId();
    }
}

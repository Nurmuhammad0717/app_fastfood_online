package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.Bonus;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.BonusService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1 + "/bonus")
@RequiredArgsConstructor
public class BonusController {

    private final BonusService bonusService;

    @GetMapping("/read")
    public ApiResult<List<BonusDTO>> read(){
        log.info("Request to courier controller read");
      return  bonusService.read();
    }

    @GetMapping("/readOne/{id}")
    public ApiResult<BonusDTO> readOne(@PathVariable Long id){
        log.info("Request to courier controller readOne with id {}", id);
        return bonusService.readOne(id);
    }

    @PostMapping("/create")
    public ApiResult<BonusDTO> create(@RequestBody BonusCrudDTO bonusCrudDTO) {
        log.info("Request to courier controller create {}", bonusCrudDTO);
      return bonusService.create(bonusCrudDTO);
    }

    @PutMapping("/update/{id}")
    public ApiResult<BonusDTO> update(@PathVariable Long id, @RequestBody BonusCrudDTO crudDTO) {
        log.info("Request to courier controller update {}", crudDTO);
        return bonusService.update(id, crudDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete( @PathVariable Long id) {
        log.info("Request to courier controller delete {}", id);
        return bonusService.delete(id);
    }

}

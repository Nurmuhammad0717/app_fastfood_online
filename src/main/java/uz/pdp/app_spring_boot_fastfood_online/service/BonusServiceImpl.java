package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Bonus;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.BonusMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.BonusRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService{

    private final BonusRepository bonusRepository;

    private final BonusMapper bonusMapper;


    @Override
    public ApiResult<BonusDTO> create(BonusCrudDTO crudDTO) {

        Optional<Bonus> bonusDB = bonusRepository.findByName(crudDTO.getName());
        if (bonusDB.isPresent())
            throw RestException.alreadyExist("Bonus with name: " + crudDTO.getName());

        Bonus bonus = new Bonus();
        bonusMapper.updateEntity(bonus, crudDTO);
        bonusRepository.save(bonus);
        return ApiResult.success(bonusMapper.toDTO(bonus));
    }

    @Override
    public ApiResult<BonusDTO> update(Long id, BonusCrudDTO crudDTO) {

        Bonus bonus = bonusRepository.findById(id).orElseThrow(() -> RestException.notFound("Bonus with id: " + id));
        bonusMapper.updateEntity(bonus, crudDTO);
        bonusRepository.save(bonus);

        return ApiResult.success(bonusMapper.toDTO(bonus));
    }

    @Override
    public ApiResult<String> delete(Long id) {
        Bonus bonus = bonusRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Bonus with id: " + id));
        bonusRepository.delete(bonus);


        return ApiResult.success("Bonus deleted");
    }

    @Override
    public ApiResult<List<BonusDTO>> read() {

        List<Bonus> bonusAll = bonusRepository.findAll();
        List<BonusDTO> bonusDtosAll = bonusAll.stream()
                .map(bonusMapper::toDTO)
                .collect(Collectors.toList());

        return ApiResult.success(bonusDtosAll);
    }

    @Override
    public ApiResult<BonusDTO> readOne(Long id) {

        Bonus bonus = bonusRepository.findById(id).orElseThrow(() -> RestException.notFound("Bonus with id: " + id));
        BonusDTO bonusDTO = bonusMapper.toDTO(bonus);

        return ApiResult.success(bonusDTO);
    }
}

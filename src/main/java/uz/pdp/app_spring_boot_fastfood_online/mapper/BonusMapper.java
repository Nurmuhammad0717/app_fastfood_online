package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Bonus;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BonusMapper {

    BonusDTO toDTO(Bonus bonus);

    Bonus toEntity(BonusDTO bonusDTO);

    void updateEntity(@MappingTarget Bonus bonus, BonusCrudDTO crudDTO);

}

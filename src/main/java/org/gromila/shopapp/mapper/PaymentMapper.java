package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.PaymentCreateDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface PaymentMapper {

    PaymentDto toDto(Payment payment);

    @Mapping(target = "id", ignore = true)
    Payment toEntity(PaymentCreateDto dto);
}

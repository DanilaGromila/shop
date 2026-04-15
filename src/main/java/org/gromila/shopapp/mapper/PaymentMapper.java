package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentMapper {

    PaymentDto toDto(Payment payment);
}

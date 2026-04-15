package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FeedbackMapper {

    FeedbackDto toDto(Feedback feedback);

    Feedback toEntity(FeedbackCreateDto dto);
}

package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.entity.Feedback;
import org.gromila.shopapp.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = ItemMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FeedbackMapper {

    FeedbackDto toDto(Feedback feedback);

    @Mapping(target = "item", source = "itemId")
    Feedback toEntity(Long itemId, FeedbackCreateDto dto);
}

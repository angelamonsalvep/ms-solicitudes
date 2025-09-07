package co.com.credilya.solicitudes.api.mapper;

import co.com.credilya.solicitudes.api.dto.SolicitudRequestDTO;
import co.com.credilya.solicitudes.api.dto.SolicitudResponseDTO;
import co.com.credilya.solicitudes.model.solicitud.Solicitud;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitudDTOMapper {

    SolicitudResponseDTO toResponseDTO(Solicitud solicitud);

    Solicitud toModel(SolicitudRequestDTO solicitudRequestDTO);
}

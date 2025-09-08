package co.com.credilya.solicitudes.api.mapper;

import co.com.credilya.solicitudes.api.dto.SolicitudRequestDTO;
import co.com.credilya.solicitudes.api.dto.SolicitudResponseDTO;
import co.com.credilya.solicitudes.api.dto.TipoPrestamoDTO;
import co.com.credilya.solicitudes.model.solicitud.Solicitud;
import co.com.credilya.solicitudes.model.tipoprestamo.TipoPrestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitudDTOMapper {

    @Mapping(target = "tipoPrestamo", expression = "java(toTipoPrestamoDTO(solicitud.getTipoPrestamo()))")
    SolicitudResponseDTO toResponseDTO(Solicitud solicitud);

    @Mapping(target = "tipoPrestamo", expression = "java(toTipoPrestamo(solicitudRequestDTO.tipoPrestamoId()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Solicitud toModel(SolicitudRequestDTO solicitudRequestDTO);

    default TipoPrestamo toTipoPrestamo(Long tipoPrestamoId) {
        if (tipoPrestamoId == null) return null;
        TipoPrestamo tipoPrestamo = new TipoPrestamo();
        tipoPrestamo.setId(tipoPrestamoId);
        return tipoPrestamo;
    }

    default TipoPrestamoDTO toTipoPrestamoDTO(TipoPrestamo tipoPrestamo) {
        if (tipoPrestamo == null) return null;
        return new TipoPrestamoDTO(
            tipoPrestamo.getId(),
            tipoPrestamo.getNombre(),
            tipoPrestamo.getDescripcion()
        );
    }
}

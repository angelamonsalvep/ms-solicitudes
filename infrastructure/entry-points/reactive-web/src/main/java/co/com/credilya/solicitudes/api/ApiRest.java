package co.com.credilya.solicitudes.api;
import co.com.credilya.solicitudes.api.dto.SolicitudRequestDTO;
import co.com.credilya.solicitudes.api.dto.SolicitudResponseDTO;
import co.com.credilya.solicitudes.api.mapper.SolicitudDTOMapper;
import co.com.credilya.solicitudes.usecase.solicitud.SolicitudUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/solicitud", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private  final SolicitudUseCase solicitudUseCase;

    private final SolicitudDTOMapper mapper;

    @PostMapping()
    public Mono<ResponseEntity<SolicitudResponseDTO>> crearSolicitud(@RequestBody SolicitudRequestDTO solicitudRequestDTO) {
        return solicitudUseCase.crearSolicitud(mapper.toModel(solicitudRequestDTO))
                .map(solicitud -> ResponseEntity
                        .status(201)
                        .body(mapper.toResponseDTO(solicitud))
                );
    }
}

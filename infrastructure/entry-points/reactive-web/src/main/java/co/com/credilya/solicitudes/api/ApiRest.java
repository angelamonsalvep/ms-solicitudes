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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/solicitud", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Tag(name = "Solicitudes", description = "Operaciones relacionadas con las solicitudes")
public class ApiRest {
    private  final SolicitudUseCase solicitudUseCase;

    private final SolicitudDTOMapper mapper;

    @PostMapping()
    @Operation(summary = "Crear una nueva solicitud", description = "Crea una solicitud y retorna la información generada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Mono<ResponseEntity<SolicitudResponseDTO>> crearSolicitud(@RequestBody SolicitudRequestDTO solicitudRequestDTO) {
        return solicitudUseCase.crearSolicitud(mapper.toModel(solicitudRequestDTO))
                .map(solicitud -> ResponseEntity
                        .status(201)
                        .body(mapper.toResponseDTO(solicitud))
                );
    }
}

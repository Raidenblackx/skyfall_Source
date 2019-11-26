package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {DocumentTypeMapper.class, UserMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "documentType.id", target = "documentTypeId")
    @Mapping(source = "documentType.documentName", target = "documentTypeDocumentName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ClientDTO toDto(Client client);

    @Mapping(target = "instructors", ignore = true)
    @Mapping(target = "removeInstructor", ignore = true)
    @Mapping(source = "documentTypeId", target = "documentType")
    @Mapping(source = "userId", target = "user")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}

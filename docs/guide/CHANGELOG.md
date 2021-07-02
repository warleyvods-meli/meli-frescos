
# Change Log
Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.


## Update - 02-06-2021


### Added
- implementação do requisito US001: COMO Representante do armazém, QUERO inserir um lote de produtos PARA
  registrar a existência do estoque.
  - criação DER versão 1: <br>
    <img src="https://github.com/warleyvods-meli/meli-frescos/blob/feature-documentation/docs/guide/diagrams/db_diagram_v1.png?raw=true" alt="DER" height="600" width="400">
  - criação do diagrama de sequência US001:
  ```mermaid
    sequenceDiagram
    Agent->>+ Controller: inbound order
    Controller->>+ Service: inbound order
    Service->> + WareHouseValidator: warehouse is valid?
    alt is valid
         WareHouseValidator ->> - AgentValidator: the agent belongs to the warehouse?
         AgentValidator ->> WareHouseValidator: ok
         alt is valid
            AgentValidator->> SectionValidator: the sector is valid?
            SectionValidator ->> AgentValidator: ok
            WareHouseValidator ->> Service: ok
        else not valid
            SectionValidator ->> AgentValidator: exception
        end
    else not valid
        WareHouseValidator ->> Service: exception
    end
    Service ->> + Repository: save inboud order
    Repository --> Service : save complete
    Service ->> -Controller: it's all right!
    Controller ->> Agent: status code 201
            
  ```

### Changed
    nada mudou
### Fixed
    nada foi corrigido

 
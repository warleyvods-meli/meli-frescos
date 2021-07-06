
# Change Log
Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.


## Update - 02-07-2021


### Added
- Implementação do Requisito US001 - Dia 1: COMO Representante do armazém, QUERO inserir um lote de produtos PARA
  registrar a existência do estoque.<br>
  - Cadastre um lote com o estoque de produtos que o compõe. Retorne o novo lote com o código de status “201 CREATED”.<br>
  POST: /api/v1/fresh-products/inboundorder/ <br>
  - Criação DER versão 1: <br>
    <img src="https://github.com/warleyvods-meli/meli-frescos/blob/master/docs/guide/diagrams/db_diagram_v1.png?raw=true" alt="DER" height="600" width="400">
  - Criação do diagrama de sequência do Requisito US001 - Dia 1:

  ```mermaid sequenceDiagram
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

## Update - 05-07-2021


### Changed
- Refatoração do Requisito US001 - Dia 1: COMO Representante do armazém, QUERO inserir um lote de produtos PARA registrar a existência do estoque. <br>
  POST: /api/v1/fresh-products/inboundorder/
  - Refatoração DER versão 2: <br>
    <img src="https://github.com/warleyvods-meli/meli-frescos/blob/master/docs/guide/diagrams/db_diagram_v2.png?raw=true" alt="DER" height="600" width="400">
  - Refatoração do diagrama de sequência do Requisito US001 - Dia 1:
  
  ```mermaid sequenceDiagram
  Agent ->>+ Controller: request inbound order
  Controller ->>+ Service: create inbound order
  alt is valid
  Service ->>+ AgentValidator: the agent is valid (Authentication)?
  else not valid
  AgentValidator -->>- Controller: agent invalid!
  end
  alt is valid
  AgentValidator ->>+ WareHouseValidator: the agent belongs to the warehouse?
  else not valid
  WareHouseValidator -->>- Controller: the agent doesn't belongs to the warehouse!
  end
  alt is valid
  AgentValidator ->>+ WareHouseValidator: the warehouse is valid?
  else not valid
  WareHouseValidator -->>- Controller: the warehouse is invalid
  end
  alt is valid
  WareHouseValidator ->>+ SectionValidator: the sector is valid?
  else not valid
  SectionValidator -->>- Controller: the sector is invalid
  end
  SectionValidator ->>+ Repository: save inbound order
  Repository -->>- Controller: status code 201
  Controller ->>+ Agent: inbound order created
  ```

### Fixed
    - Diagrama DER - US001
    - Diagrama de Sequência do Requisito US001 - Dia 1

## Update - 07-07-2021

### Added
- Implementação do Requisito US002 - Dia 1: COMO Representante do armazém, QUERO atualizar um lote de produtos do estoque.
  - criação do diagrama de sequência US002: Caso o lote já exista e deva ser atualizado. Retorne o estoque atualizado com o código de status “201 CREATED”.

  ```mermaid sequenceDiagram
  Agent ->>+ Controller: request update inbound order
  Controller ->>+ Service: find inbound order by id
  alt is valid
  Service ->>+ AgentValidator: the agent is valid (Authentication)?
  else not valid
  AgentValidator -->>- Controller: agent is invalid!
  end
  alt is valid
  AgentValidator ->>+ WareHouseValidator: the agent belongs to the warehouse?
  else not valid
  WareHouseValidator -->>- Controller: the agent doesn't belongs to the warehouse!
  end
  alt is valid
  AgentValidator ->>+ WareHouseValidator: the warehouse is valid?
  else not valid
  WareHouseValidator -->>- Controller: the warehouse is invalid
  end
  alt is valid
  WareHouseValidator ->>+ SectionValidator: the sector is valid?
  else not valid
  SectionValidator -->>- Controller: the sector is invalid
  end
  alt is valid
  SectionValidator ->>+ Repository: inbound order is valid?
  Repository ->> Controller: inbound order found - status code 201
  else not valid
  Repository -->>- Controller: inbound order Not Found
  end
  Controller ->>+ Agent: response inbound order updated 
  ``` 

### Fixed
    - Diagrama de Sequência do Requisito US002 - Dia 1

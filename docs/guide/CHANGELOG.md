

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
Agent->>+ Controller: inbound order Controller->>+ Service: inbound order Service->> + WareHouseValidator: warehouse is valid? alt is valid WareHouseValidator ->> - AgentValidator: the agent belongs to the warehouse? AgentValidator ->> WareHouseValidator: ok alt is valid AgentValidator->> SectionValidator: the sector is valid? SectionValidator ->> AgentValidator: ok WareHouseValidator ->> Service: ok else not valid SectionValidator ->> AgentValidator: exception end else not valid WareHouseValidator ->> Service: exception end Service ->> + Repository: save inboud order Repository --> Service : save complete Service ->> -Controller: it's all right! Controller ->> Agent: status code 201 ```
## Update - 05-07-2021


### Changed
- Refatoração do Requisito US001 - Dia 1: COMO Representante do armazém, QUERO inserir um lote de produtos PARA registrar a existência do estoque. <br>  
  POST: /api/v1/fresh-products/inboundorder/
  - Refatoração DER versão 2: <br>  
    <img src="https://github.com/warleyvods-meli/meli-frescos/blob/master/docs/guide/diagrams/db_diagram_v2.png?raw=true" alt="DER" height="600" width="400">
  - Refatoração do diagrama de sequência do Requisito US001 - Dia 1:

  ```mermaid sequenceDiagram  
Agent ->>+ Controller: request inbound order Controller ->>+ Service: create inbound order alt is valid Service ->>+ AgentValidator: the agent is valid (Authentication)? else not valid AgentValidator -->>- Controller: agent invalid! end alt is valid AgentValidator ->>+ WareHouseValidator: the agent belongs to the warehouse? else not valid WareHouseValidator -->>- Controller: the agent doesn't belongs to the warehouse! end alt is valid AgentValidator ->>+ WareHouseValidator: the warehouse is valid? else not valid WareHouseValidator -->>- Controller: the warehouse is invalid end alt is valid WareHouseValidator ->>+ SectionValidator: the sector is valid? else not valid SectionValidator -->>- Controller: the sector is invalid end SectionValidator ->>+ Repository: save inbound order Repository -->>- Controller: status code 201 Controller ->>+ Agent: inbound order created ```
### Fixed
- Diagrama DER - US001 - Diagrama de Sequência do Requisito US001 - Dia 1
## Update - 07-07-2021

### Added
- Implementação do Requisito US002 - Dia 1: COMO Representante do armazém, QUERO atualizar um lote de produtos do estoque.

  - Criação do diagrama de sequência US002: Caso o lote já exista e deva ser atualizado. Retorne o estoque atualizado com o código de status “201 CREATED”.

  ```mermaid sequenceDiagram  
Agent ->>+ Controller: request update inbound order Controller ->>+ Service: find inbound order by id alt is valid Service ->>+ AgentValidator: the agent is valid (Authentication)? else not valid AgentValidator -->>- Controller: agent is invalid! end alt is valid AgentValidator ->>+ WareHouseValidator: the agent belongs to the warehouse? else not valid WareHouseValidator -->>- Controller: the agent doesn't belongs to the warehouse! end alt is valid AgentValidator ->>+ WareHouseValidator: the warehouse is valid? else not valid WareHouseValidator -->>- Controller: the warehouse is invalid end alt is valid WareHouseValidator ->>+ SectionValidator: the sector is valid? else not valid SectionValidator -->>- Controller: the sector is invalid end alt is valid SectionValidator ->>+ Repository: inbound order is valid? Repository ->> Controller: inbound order found - status code 201 else not valid Repository -->>- Controller: inbound order Not Found end Controller ->>+ Agent: response inbound order updated```
### Fixed
- Diagrama de Sequência do Requisito US002 - Dia 1### Added
- Implementação do Requisito 2 User Story - Dia 2: COMO comprador, QUERO adicionar produtos ao carrinho de compras do  
  Marketplace PARA comprá-los, se desejar.  
  POST:
  - Criação do diagrama de sequência User Story <br>

  ```mermaid sequenceDiagram  
Seller ->>+ Controller: request add product to cart alt is valid Controller ->>+ Service: product is registered? else not valid Service -->>- Controller: product isn't registered! end alt is valid Controller ->>+ Service: buyer is registered? else not valid Service -->>- Controller: buyer isn't registered! end alt is valid Controller ->>+ Service: there is product stock? else not valid Service -->>- Controller: there isn't product stock! end alt is valid Controller ->>+ Service: expiration date is < 3 weeks? else not valid Service -->>- Controller: expiration date expired! end Service ->>+ Cart: add products in cart Cart -->>- Controller: cart with products Controller ->>+ Seller: response cart with products ```
### Added
- Implementação do Requisito 2 US001 - Dia 2: Exibir uma lista completa de produtos.   
  Se a lista não existir, ela deve retornar um “404 Not Found”. <br>  
  GET: /api/v1/fresh-products/
  - Criação do diagrama de sequência Requisito 2 US001 - Dia 2. <br>

  ```mermaid sequenceDiagram  
Buyer ->>+ Controller: request list of products alt is valid Service ->>+ ProductRepository: product exists? ProductRepository -->> Controller: list of products else not valid ProductRepository -->>- Controller: status code 404 end Controller ->>+ Buyer: response list of products ```
### Added
- Implementação do Requisito 2 US002 - Dia 2: lista de produtos por categoria:<br>  
  FS = Fresh <br>  
  RF = Refrigerado <br>  
  FF = Congelado <br>  
  Se a lista não existir, ela deve retornar um “404 Not Found”. <br>  
  GET: /api/v1/fresh-products/list?querytype=[categoraa producto]
- Criação do diagrama de sequência Requisito 2 US002 - Dia 2. <br>

```mermaid sequenceDiagram  
Buyer ->>+ Controller: request list of products by category alt is valid Service ->>+ ProductRepository: product exists? ProductRepository -->> Controller: list of products else not valid ProductRepository -->>- Controller: status code 404 end Controller ->>+ Buyer: response list of products by category  
```


### Added
- Implementação do Requisito 2 US003 - Dia 2: Registre um pedido com a lista de produtos que compõem o  
  PurchaseOrder. Calcule o preço final e devolva-o junto com um código de status “201 CREATED”.  
  Se não houver estoque de um produto, notifique a situação retornando um erro por produto, não  
  no nível do pedido. <br>  
  POST: /api/v1/fresh-products/orders/
  - Criação do diagrama de sequência Requisito 2 US003 - Dia 2. <br>

  ```mermaid sequenceDiagram  
Buyer ->>+ Controller: request create purchcase order with list of products alt is valid Service ->>+ StockRepository: there is stock of product? StockRepository -->> Controller: price + status code 201 else not valid StockRepository -->>- Controller: status code 400 end Controller ->>+ Buyer: response purchcase order with list of products ```
### Added
- Implementação do Requisito 2 US004 - Dia 2: Mostrar produtos no pedido. <br>  
  GET: /api/v1/fresh-products/orders/querytype=[idOrder]
- Criação do diagrama de sequência Requisito 2 US004 - Dia 2. <br>

```mermaid sequenceDiagram  
Buyer ->>+ Controller: request get list of products Controller ->>+ Service: Service ->>+ PurchaseOrderRepository: get list product from purchase order PurchaseOrderRepository -->>- Controller: list product from purchase order Controller ->>+ Buyer: response list of products
 ```
### Added
- Implementação do Requisito 2 US005 - Dia 2: Modifique o pedido existente. Que  
  seja do tipo carrinho para modificar. <br>  
  PUT: /api/v1/fresh-products/orders/query param=[idOrder]
- Criação do diagrama de sequência Requisito 2 US005 - Dia 2. <br>

```mermaid sequenceDiagram  
Buyer ->>+ Controller: request put purchase order alt is valid Service ->>+ PurchaseRepository: purchase type cart? PurchaseRepository -->> Controller: purchase modified - status code 201 else not valid PurchaseRepository -->>- Controller: purchase not found - status code 404 end Controller ->>+ Buyer: response purchase order modified
 ```
## Update - 08-07-2021


### Added
- Implementação dos requisitos 3 e 4
- implementação do Spring Security
- geração do diagrama de classes: <br>
<img src="https://github.com/warleyvods-meli/meli-frescos/blob/master/docs/guide/diagrams/class_diagram.png?raw=true" alt="CLASS_DIAGRAM" height="600" width="400">  


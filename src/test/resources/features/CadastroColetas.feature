# language: pt
Funcionalidade: Cadastro de nova coleta
  Como usuário da API de coleta de lixo
  Quero cadastrar uma nova coleta
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de coleta
    Dado que eu tenha os seguintes dados da coleta:
      | campo          |  valor                                |
      | dataColeta     |  2024-10-30T21:40:43.000+00:00        |
      | dataRegistro   |  2024-10-30T21:40:43.000+00:00        |
      | nome_bairro    |  Centro                               |
      | numero_volume  |  5                                    |
      | rota           |  null                                 |
    Quando eu enviar a requisição para o endpoint "/agendamento" de agendamento de coletas
    Então o status code da resposta deve ser 201

  Cenário: Cadastro sem sucesso ao passar o campo nome_bairro invalido
    Dado que eu tenha os seguintes dados da coleta:
      | campo          |  valor                                |
      | dataColeta     |  2024-10-30T21:40:43.000+00:00        |
      | dataRegistro   |  2024-10-30T21:40:43.000+00:00        |
      | nome_bairro    |                                       |
      | numero_volume  |  5                                    |
      | rota           |  null                                 |
    Quando eu enviar a requisição para o endpoint "/agendamento" de agendamento de coletas
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Nome do bairro inválido"

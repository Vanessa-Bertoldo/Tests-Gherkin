# language: pt
Funcionalidade: Deletar uma coleta
  Como usuário da API
  Quero conseguir deletar uma coleta
  Para que o registro seja apagado corretamente no sistema
  Contexto: Cadastro bem-sucedido de coleta
    Dado que eu tenha os seguintes dados da coleta:
      | campo          |  valor                                |
      | dataColeta     |  2024-10-30T21:40:43.000+00:00        |
      | dataRegistro   |  2024-10-30T21:40:43.000+00:00        |
      | nome_bairro    |  Centro                               |
      | numero_volume  |  5                                    |
      | rota           |  null                                 |
    Quando eu enviar a requisição para o endpoint "/agendamento" de cadastro de coleta
    Então o status code da resposta deve ser 201

  Cenário: Deve ser possível deletar uma coleta
    Dado que eu recupere o ID da coleta criada no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/agendamento" de deleção de coleta
    Então o status code da resposta deve ser 204
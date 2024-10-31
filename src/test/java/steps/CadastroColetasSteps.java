package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErroMessageModel;
import org.junit.Assert;
import services.CadastroColetasService;

import java.util.List;
import java.util.Map;

public class CadastroColetasSteps {
    CadastroColetasService cadastroColetasService = new CadastroColetasService();

    @Dado("que eu tenha os seguintes dados da coleta:")
    public void queEuTenhaOsSeguintesDadosDaColeta(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows){
            cadastroColetasService.setFieldsColeta(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de agendamento de coletas")
    public void euEnviarARequisiçãoParaOEndpointDeAgendamentoDeColetas(String endPoint) {
        cadastroColetasService.createColeta(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroColetasService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErroMessageModel erroMessageModel = cadastroColetasService.gson.fromJson(
                cadastroColetasService.response.jsonPath().prettify(), ErroMessageModel.class
        );
        Assert.assertEquals(message, erroMessageModel.getMessage());
    }

//    @Dado("que eu tenha os seguintes dados da coleta:")
//    public void queEuTenhaOsSeguintesDadosDaEntrega() {
//    }
//
//    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de coleta")
//    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEntregas(String arg0) {
//    }

    @Dado("que eu recupere o ID da coleta criada no contexto")
    public void queEuRecupereOIDDaColetaCriadaNoContexto() {
        cadastroColetasService.retriverIdDelivery();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de coleta")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeColeta(String endPoint) {
        cadastroColetasService.deleteColeta(endPoint);
    }
}

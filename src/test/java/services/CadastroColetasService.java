package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.ColetaModel;

import static io.restassured.RestAssured.given;

public class CadastroColetasService {

    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1c3VhcmlvIiwic3ViIjoiYWRtaW5AZ21haWwuY29tIiwiZXhwIjoxNzMwNDE5MjAwfQ.cQmQTjVoKODe0Sh6nu8Isev5Tj8jImg6SJmcXj59CYI";
    final ColetaModel coleta = new ColetaModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;
    String baseUrl = "http://localhost:8080/v1/coletas";
    String idColeta;

    public void setFieldsColeta(String field, String value) {
        switch (field) {
            case "dataColeta" ->  coleta.setDataColeta(value);
            case "dataRegistro" -> coleta.setDataRegistro(value);
            case "nome_bairro" -> coleta.setNome_bairro(value);
            case "numero_volume" -> coleta.setNumero_volume(Double.parseDouble(value));
            case "rota" -> coleta.setRota(null);
            default -> throw new IllegalStateException("Campo inesperado: " + field);
        }
    }

    public void createColeta(String endPoint) {

        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(coleta);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
        System.out.println("Corpo da requisição: " + url);

    }

    public void deleteColeta(String endPoint) {
        String url = baseUrl + endPoint;
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void retriverIdDelivery(){
        idColeta = String.valueOf(gson.fromJson(response.jsonPath().prettify(), ColetaModel.class).getId_coleta());
    }
}
